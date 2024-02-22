package com.qestit.utils;

//ApiUtils.java

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qestit.constants.FrameworkConstants;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ApiUtils {

 private static final Map<String, String> userCredentialsStorage = new HashMap<>();

 private static final String API_BASE_URL = FrameworkConstants.URL_ECOMMERCE;
 private static final String REGISTER_ENDPOINT = FrameworkConstants.API_ENDPOINT;

 public String registerUser(Map<String, String> parameters) {
     StringBuilder requestBody = new StringBuilder("{");
     for (Map.Entry<String, String> entry : parameters.entrySet()) {
         requestBody.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\",");
     }
     requestBody.deleteCharAt(requestBody.length() - 1);
     requestBody.append("}");

     HttpRequest request = HttpRequest.newBuilder()
             .uri(URI.create(API_BASE_URL + REGISTER_ENDPOINT))
             .header("Content-Type", "application/json")
             .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
             .build();

     try {
         HttpClient client = HttpClient.newHttpClient();
         HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

         if (response.statusCode() == 201) {
             String userCredentials = extractUserCredentialsFromResponse(response.body());
             userCredentialsStorage.put(parameters.get("email"), userCredentials);
             return userCredentials;
         } else {
             System.out.println("Registration failed. Response code: " + response.statusCode());
             System.out.println("Response body: " + response.body());
             return null;
         }
     } catch (IOException | InterruptedException e) {
         e.printStackTrace();
         return null;
     }
 }

 private String extractUserCredentialsFromResponse(String responseBody) {
     try {
         JsonNode jsonNode = new ObjectMapper().readTree(responseBody);
         JsonNode tokenNode = jsonNode.get("token");

         if (tokenNode != null) {
             return tokenNode.asText();
         } else {
             System.out.println("Token not found in the response.");
             return null;
         }
     } catch (IOException e) {
         e.printStackTrace();
         return null;
     }
 }
}

