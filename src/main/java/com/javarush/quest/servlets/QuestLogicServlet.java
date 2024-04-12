package com.javarush.quest.servlets;

import com.javarush.quest.logics.QuestService;
import com.javarush.quest.objects.Quest;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/questLogic")
public class QuestLogicServlet extends BaseQuestServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();

        QuestService service = getQuestServiceFromSession(session);
        Quest.Decision decision = getCurrentDecision(req, service);

        Quest.Option option = getSelectedOption(req, decision);

        processOption(option, session, service, resp, req);
    }

    private void processOption(Quest.Option option, HttpSession session, QuestService service, HttpServletResponse resp, HttpServletRequest req) throws IOException, ServletException {
        if(isQuestEnd(option)) {
            resp.sendRedirect(FINISH_QUEST_END_POINT);
        } else {
            updateSessionForNextStep(option, session);
            defineBGImage(session, service, getServletContext());
            forwardToQuestPage(req, resp);
        }
    }

    private void forwardToQuestPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(QUEST_JSP_REQUEST_PATH);
        dispatcher.forward(req, resp);
    }

    private void updateSessionForNextStep(Quest.Option option, HttpSession session) {
        session.setAttribute(DECISION_ATTRIBUTE_NAME, option.next());
        session.setAttribute(TITLE_ATTRIBUTE_NAME, option.title());
        session.setAttribute(STORY_ATTRIBUTE_NAME, option.story());
    }

    private boolean isQuestEnd(Quest.Option option) {
        return QUEST_END_ATTRIBUTE_NAME.equals(option.next());
    }

    private Quest.Option getSelectedOption(HttpServletRequest req, Quest.Decision decision) {
        int choice = Integer.parseInt(OPTION_CHOICE_PARAMETER_NAME);
        return  decision.options().get(choice);
    }

    private Quest.Decision getCurrentDecision(HttpServletRequest req, QuestService service) {
        String decisionKey = req.getParameter(CHOICE_CONTEXT_PARAMETER_NAME);
        System.out.println(decisionKey);

        return Objects.isNull(decisionKey) ? service.getDecision(DEFAULT_DECISION) : service.getDecision(decisionKey);
    }

    private QuestService getQuestServiceFromSession(HttpSession session) {
        return (QuestService) session.getAttribute(QUEST_SERVICE_ATTRIBUTE_NAME);
    }
}
