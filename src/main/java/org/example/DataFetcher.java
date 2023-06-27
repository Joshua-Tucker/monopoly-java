package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataFetcher {

    private static final String JSON_FILE_PATH = "src/main/java/monopoly.json";

    public static Data.GameData fetchData() {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(JSON_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Data.GameData gameData = gson.fromJson(content.toString(), Data.GameData.class);

        if (gameData != null && gameData.spaces != null) {
            for (Data.Space space : gameData.spaces) {
                space.setAvailable(!space.type.contains("Special"));

            }
        }

        return gameData;
    }

}
