package com.javarush.quest.servlets;

import com.javarush.quest.logics.QuestService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public abstract class BaseQuestServlet extends HttpServlet {
    protected static final String QUEST_JSP_REQUEST_PATH = "/WEB-INF/views/quest.jsp";
    protected static final String QUEST_FINISH_JSP_REQUEST_PATH = "/WEB-INF/views/finishQuest.jsp";
    protected static final String FINISH_QUEST_END_POINT = "finishQuest";
    protected static final String QUEST_SERVICE_ATTRIBUTE_NAME = "questService";
    protected static final String DECISION_ATTRIBUTE_NAME = "decision";
    protected static final String OPTION_CHOICE_PARAMETER_NAME = "choiceIndex";
    protected static final String CHOICE_CONTEXT_PARAMETER_NAME = "choiceContext";
    protected static final String TITLE_ATTRIBUTE_NAME = "title";
    protected static final String STORY_ATTRIBUTE_NAME = "story";
    protected static final String QUEST_END_ATTRIBUTE_NAME = "end";
    protected static final String QUEST_CONTAINER_BACKGROUND_IMAGE_NAME = "questContainerBackground";
    protected static final String DEFAULT_QUEST_FILE = "quest1.json";
    protected static final String DEFAULT_DECISION = "root";

    protected void defineBGImage(HttpSession session, QuestService service, ServletContext context) {
        String decisionKey = (String) session.getAttribute(DECISION_ATTRIBUTE_NAME);
        Optional<String> name = service.getNameForBGImage(decisionKey, context);

        name.ifPresent(backgroundName -> session.setAttribute(QUEST_CONTAINER_BACKGROUND_IMAGE_NAME, backgroundName));
    }
}
