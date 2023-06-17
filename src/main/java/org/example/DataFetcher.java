package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class DataFetcher {

    private static final String GIST_URL = "https://gist.github.com/nologyRob/4dde72cd9da51b48dc797f01d5e49fd3/raw";

    public static Data.GameData fetchData() {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(GIST_URL).openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Data.GameData gameData = gson.fromJson(content.toString(), Data.GameData.class);

        // Set availability to true for all spaces
        if (gameData != null && gameData.spaces != null) {
            for (Data.Space space : gameData.spaces) {
                space.setAvailable(true);
            }
        }

        return gameData;
    }

}
