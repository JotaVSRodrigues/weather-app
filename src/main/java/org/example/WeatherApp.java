package org.example;

/*
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class WeatherApp {
    /*
    classe que estabelece conexão com as API's e retorna objetos JSON, que serão
    tratados e atualizados na interface GUI
    */
/*
    // method that stabilish connection with API
    public JSONArray apiConnection(String urlPath) {
        urlPath = urlPath.replace(" ", "+");
        JSONObject resultJsonObj = new JSONObject();
        JSONArray locationData = new JSONArray();
        JSONParser jsonParser = new JSONParser();
        // InputStream to read API responses
        // HttpURLConnection.HTTP_OK to check if it works
        // close() to stop the reading of InputStream -> BufferedReader or Scanner
        // disconnect() to finish connection

        // receive the url path from another method (to a specified API) and try a HttpURLConnection
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // check if the server status is OK
            if (conn.getResponseCode() == 200) {
                StringBuilder sb = new StringBuilder();
                Scanner scan = new Scanner(conn.getInputStream());

                // scan the http request response, turning it into String data
                while (scan.hasNext()) {
                    sb.append(scan.nextLine());
                }

                // parse the StringBuilder into a Json Object
                resultJsonObj = (JSONObject) jsonParser.parse(String.valueOf(sb));

                // get the list of location data the API generated from the location name
                locationData = (JSONArray) resultJsonObj.get("results");

                // closing scanner and URL connection
                conn.disconnect();
                scan.close();

                // returning an Array of JSON data
                return locationData;
            } else {
                JOptionPane.showMessageDialog(null,
                        "it wasn't possible to stabilish connection with the API - reponse code: "
                                + conn.getResponseCode());
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String omdbConnection(String movieName) {
        try {
            String url = "http://www.omdbapi.com/?apikey=60fbb09c&t=" + movieName;

            String movieData = apiConnection(url).toJSONString();
            return movieData;
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("não foi possível buscar um filme");
        }
        return "API do omdb não está funcionando";
    }
}
*/