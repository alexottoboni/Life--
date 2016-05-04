package com.lifemm.game;

import java.net.URL;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonReader;

public class ScoreAPI {

    public static void saveScore(String name, int score) throws Exception {
        String url = "http://lime.taysoftware.website?name=" + name + "&score=" + score;
        String response = doHttpUrlConnectionAction(url);
    }

    public static List<Score> getScores() {
        LinkedList<Score> result = new LinkedList<Score>();
        try {
            String url = "http://lime.taysoftware.website/";
            String response = doHttpUrlConnectionAction(url);
            JsonReader reader = new JsonReader();
            JsonValue head = reader.parse(response);
            head = head.get("highscores");
            for (JsonValue entry = head.child; entry != null; entry = entry.next) {
                result.add(new Score(entry.getString("name"), entry.getInt("score")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    private static String doHttpUrlConnectionAction(String desiredUrl) throws Exception {
        URL url = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder;
 
        try {
            url = new URL(desiredUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
           
            connection.setReadTimeout(15*1000);
            connection.connect();
     
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();
     
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
}
