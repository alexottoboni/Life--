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

public class ScoreAPI {

    public static void saveScore(String name, int score) {
        try {
            String url = "http://www.taysoftware.website/LiMe/index.php?name=" + name + "&score=" + score;
            String response = doHttpUrlConnectionAction(url);
            System.out.println(url);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Score> getScores() {
        try {
            String url = "http://www.taysoftware.website/LiMe/index.php";
            String response = doHttpUrlConnectionAction(url);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
