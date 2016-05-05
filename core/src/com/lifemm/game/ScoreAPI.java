package com.lifemm.game;

import java.net.URL;
import java.util.List;
import java.net.HttpURLConnection;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonReader;

public class ScoreAPI {
    private static ScoreAPI instance;

    private ScoreAPI() {

    }

    /**
    Gets or creates an instance of the ScoreAPI.
    @return The instance of ScoreAPI.
    */
    public static ScoreAPI getInstance() {
        if (instance == null) {
            instance = new ScoreAPI();
        }
        return instance;
    }
    /**
    Saves a high score. Blocking, so do in separate thread.
    @param name The name of the player to save.
    @param score The score the player earned.
    */
    public void saveScore(String name, int score) throws IOException {
        String url = "http://lime.taysoftware.website?name=" + name + "&score=" + score;
        doHttpUrlConnectionAction(url);
    }

    /**
    Returns a list of high scores.
    @return A List of Scores.
    */
    public List<Score> getScores() {
        LinkedList<Score> result = new LinkedList<>();
        try {
            String url = "http://lime.taysoftware.website/";
            String response = doHttpUrlConnectionAction(url);
            JsonReader reader = new JsonReader();
            JsonValue head = reader.parse(response);
            head = head.get("highscores");
            for (JsonValue entry = head.child; entry != null; entry = entry.next) {
                result.add(new Score(entry.getString("name"), entry.getInt("score")));
            }
        } catch (IOException e) {
            System.out.println("Failed to get high scores. Go pester Wyatt.");
        }
        return result;
    }
    
    /**
    Does a http GET request.
    @param desiredUrl The url that will be hit by the method.
    */
    private String doHttpUrlConnectionAction(String desiredUrl) throws IOException {
        URL url = null;
        StringBuilder stringBuilder;
        url = new URL(desiredUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
           
        connection.setReadTimeout(15*1000);
        connection.connect();
 
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {    
            stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
