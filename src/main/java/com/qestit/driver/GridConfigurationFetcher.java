package com.qestit.driver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.qestit.constants.FrameworkConstants;

public class GridConfigurationFetcher {

    private static final String GRID_HUB_STATUS_URL = "http://" + FrameworkConstants.REMOTE_URL + ":" + FrameworkConstants.REMOTE_PORT + "/status";

    public static List<String> fetchAvailableBrowsers() {
        List<String> availableBrowsers = new ArrayList<>();
        try {
            URL url = new URL(GRID_HUB_STATUS_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed to connect to the Grid Hub: HTTP error code : " + connection.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output;
            StringBuilder response = new StringBuilder();
            while ((output = br.readLine()) != null) {
                response.append(output);
            }

            connection.disconnect();

            // Parse the response to extract available browsers
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(response.toString());
            JSONObject value = (JSONObject) jsonObject.get("value");
            JSONArray nodes = (JSONArray) value.get("nodes");
            for (Object node : nodes) {
                JSONObject nodeObj = (JSONObject) node;
                JSONArray slots = (JSONArray) nodeObj.get("slots"); // Accessing slots instead of sessions
                for (Object slot : slots) {
                    JSONObject slotObj = (JSONObject) slot;
                    JSONObject stereotype = (JSONObject) slotObj.get("stereotype");
                    String browserName = (String) stereotype.get("browserName");
                    if (!availableBrowsers.contains(browserName)) {
                        availableBrowsers.add(browserName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return availableBrowsers;
    }
}
