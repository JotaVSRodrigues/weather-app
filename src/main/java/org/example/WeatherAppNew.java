package org.example;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class WeatherAppNew {
    // fetch weather data for given location
    public static JSONObject getWeatherData(String locationName) {
        // get location coordinates using the geolocation API
        JSONArray locationData = getLocationData(locationName);

        // extract latitude and longitude data
        JSONObject location = (JSONObject) locationData.get(0);
        double latitude = (double) location.get("latitude");
        double longitude = (double) location.get("longitude");

        // build API request URL with location coordinates
        String urlString = "https://api.open-meteo.com/v1/forecast?" +
                "latitude=" + latitude + "&longitude=" + longitude +
                "&hourly=temperature_2m,weather_code,relative_humidity_2m,wind_speed_10m&timezone=America%2FSao_Paulo";
        try {
            JSONObject hourly = (JSONObject) conn(urlString).get("hourly");
            if (hourly == null){
                System.out.println("hourly é nulo");
            }
            /*
            * We want to get the current hours data
            * so we need to get the index of our current hour
            */
            JSONArray time = (JSONArray) hourly.get("time");
            int index = findIndexOfCurrentTime(time);

            // get temperature
            JSONArray temperatureData = (JSONArray) hourly.get("temperature_2m");
            double temperature = (double) temperatureData.get(index);

            // get weather code
            JSONArray weatherCode = (JSONArray) hourly.get("weather_code");
            String weatherCondition = convertWeatherCode((long) weatherCode.get(index));

            // get humidity
            JSONArray relativeHumidity = (JSONArray) hourly.get("relative_humidity_2m");
            long humidity = (long) relativeHumidity.get(index);

            // get windspeed
            JSONArray windSpeedData = (JSONArray) hourly.get("wind_speed_10m");
            double windSpeed = (double) windSpeedData.get(index);

            // build the weather json data object that we are going to access
            JSONObject weatherData = new JSONObject();
            weatherData.put("temperature", temperature);
            weatherData.put("weather_condition", weatherCondition);
            weatherData.put("humidity", humidity);
            weatherData.put("wind_speed", windSpeed);

            return weatherData;

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("get weather data not working!");
        return null;
    }

    public static JSONArray getLocationData(String locationName) {
        // replace any whitespace in location name to + to adwhere to API's request format
        locationName = locationName.replace(" ", "+");

        // build API url with location parameter
        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" +
                locationName + "&count=10&language=pt&format=json";

        try {
            JSONArray locationData = (JSONArray) conn(urlString).get("results");
            return locationData;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // could not find location
        System.out.println("get location data not working!");
        return null;
    }

    private static HttpURLConnection fetchApiResponse(String urlString) {
        try {
            // attempt to create connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // set request method to get
            conn.setRequestMethod("GET");

            // connect to our API
            conn.connect();
            return conn;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // could not make connection
        System.out.println("fetch api response not working!");
        return null;
    }

    private static JSONObject conn(String urlString) {
        try {
            // call api and get response
            HttpURLConnection conn = fetchApiResponse(urlString);

            // check for response status
            // 200 - means that the connection was a sucess
            if (conn.getResponseCode() != 200) {
                System.out.println("Error: could not connect to API");
                return null;
            }

            // store resulting json data
            StringBuilder resultJson = new StringBuilder();
            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNext()) {
                resultJson.append(scanner.nextLine());
            }

            // close scanner
            scanner.close();

            // close url connection
            conn.disconnect();

            // parse through our data
            JSONParser parser = new JSONParser();
            JSONObject resultJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

            return resultJsonObj;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static int findIndexOfCurrentTime(JSONArray timeList) {
        String currentTime = getCurrentTime();

        // iterate throug the line list and see which one matches our current line
        for (int i = 0; i < timeList.size(); i++) {
            String time = (String) timeList.get(i);
            if (time.equalsIgnoreCase(currentTime)) return i;
        }

        return 0;
    }

    public static String getCurrentTime() {
        // get current data and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // format date to be 2026-01-05T03:00 (this is how it is read int the API)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':00'");

        // format and print the current data and time
        return currentDateTime.format(formatter);
    }

    // convert the weather code to something more readable
    private static String convertWeatherCode(long weatherCode) {
        String weatherCondition = "";

        if (weatherCode == 0L) {
            // clear
            weatherCondition = "Clear";
        } else if (weatherCode <= 3L && weatherCode > 0L) {
            // cloudy
            weatherCondition = "Cloudy";
        } else if (weatherCode >= 51L && weatherCode <= 67L
                || weatherCode >= 86L && weatherCode <= 99L) {
            // rain
            weatherCondition = "Rainy";
        } else if (weatherCode >= 71L && weatherCode <= 77L) {
            // show
            weatherCondition = "Snowy";
        }

        return weatherCondition;
    }
}
