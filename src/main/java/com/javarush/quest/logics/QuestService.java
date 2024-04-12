package com.javarush.quest.logics;

import com.javarush.quest.repository.QuestRepo;
import jakarta.servlet.ServletContext;
import com.javarush.quest.objects.Quest.*;
import com.javarush.quest.objects.Quest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class QuestService {
    private final Quest quest;

    public QuestService(String questName) throws IOException {
        var repository = new QuestRepo(questName);
        quest = repository.getQuest();
    }
    public String getStory() {return quest.story();}
    public String getStory(Option option) {return option.story();}
    public String getTitle() {return quest.title();}
    public String getTitle(Option option) {return option.title();}
    public Decision getDecision(String key) {
        return quest.decisions().get(key);
    }
    public String getPrompt(Decision decision) {return decision.prompt();}
    public String getPrompt(String decision) {return quest.decisions().get(decision).prompt();}
    public List<Option> getOptions(String decision) { return quest.decisions().get(decision).options();}
    public String getNextKey(Option option) { return option.next();}
    public Optional<String> getNameForBGImage(String decision, ServletContext context) {
        String fileName = decision + ".jpg";
        String imagePathDir = "/static/images/";
        String newBGPath = context.getRealPath(imagePathDir + fileName);

        File file = new File(newBGPath);
        if(file.exists()){
            return Optional.of(fileName);
        }
        return Optional.empty();
    }
}
