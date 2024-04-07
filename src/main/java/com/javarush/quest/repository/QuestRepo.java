package com.javarush.quest.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.javarush.quest.objects.Quest;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
@Getter
public class QuestRepo {
    private final Quest quest;
    public QuestRepo(String pathToQuest) throws IOException {
        this.quest = loadQuestData(pathToQuest);
    }
    private Quest loadQuestData(String path) throws IOException {
        ClassLoader loader = getClass().getClassLoader();
        try(InputStream imputStream = loader.getResourceAsStream(path)) {
            assert imputStream != null;
            try (Reader reader = new InputStreamReader(imputStream)){
                Gson gson = new Gson();
                Type questMapType = new TypeToken<Quest>(){}.getType();
                return gson.fromJson(reader, questMapType);
            }
        }
    }
}
