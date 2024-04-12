package com.javarush.quest.servlets;

import com.javarush.quest.logics.QuestService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class InitQuestServlet extends BaseQuestServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        initQuest(req);
        req.getRequestDispatcher(QUEST_JSP_REQUEST_PATH).forward(req, resp);
    }

    private void initQuest(HttpServletRequest req) throws IOException {
        HttpSession session = req.getSession();
        QuestService service = new QuestService(DEFAULT_QUEST_FILE);

        session.setAttribute(QUEST_SERVICE_ATTRIBUTE_NAME, service);
        session.setAttribute(DECISION_ATTRIBUTE_NAME, DEFAULT_DECISION);
        session.setAttribute(TITLE_ATTRIBUTE_NAME, service.getTitle());
        session.setAttribute(STORY_ATTRIBUTE_NAME, service.getStory());

        defineBGImage(session, service, getServletContext());
    }

}
