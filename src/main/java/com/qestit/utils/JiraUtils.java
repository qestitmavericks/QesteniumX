/*
 * Copyright (c) 2023 QESTIT
 * QesteniumX
 */

package com.qestit.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.qestit.constants.FrameworkConstants;

public class JiraUtils {
    private String jiraURL = FrameworkConstants.JIRA_URL;
    private String jiraUserName = FrameworkConstants.JIRA_USERNAME;
    private String jiraPassword = FrameworkConstants.JIRA_PASSWORD;

    public String createJiraIssue(Map<String, Object> fields) throws IOException, ParseException, org.apache.hc.core5.http.ParseException {
        String issueId = null;
        JSONObject jsonPayload = new JSONObject();
        jsonPayload.put("fields", convertMapToJSON(fields));

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost postRequest = new HttpPost(jiraURL + "/rest/api/3/issue");
            postRequest.addHeader("Content-Type", "application/json");
            String encoding = Base64.getEncoder().encodeToString((jiraUserName + ":" + jiraPassword).getBytes(StandardCharsets.UTF_8));
            postRequest.setHeader("Authorization", "Basic " + encoding);

            StringEntity params = new StringEntity(jsonPayload.toJSONString()); // Changed to jsonPayload
            postRequest.setEntity(params);

            try (var response = httpClient.execute(postRequest)) {
                int statusCode = response.getCode();
                String responseString = EntityUtils.toString(response.getEntity());
                LogUtils.info("JIRA Response Status: " + statusCode + ", Body: " + responseString);

                if (statusCode == 200 || statusCode == 201) {
                    JSONParser parser = new JSONParser();
                    JSONObject json = (JSONObject) parser.parse(responseString);
                    issueId = (String) json.get("key");
                }
            }
        }

        return issueId;
    }
    
    // this is Overloaded method to handle the new extra added fileds in Jira
//    public String createJiraIssueJsonObject(JSONObject jsonPayload) throws IOException, ParseException, org.apache.hc.core5.http.ParseException {
//        String issueId = null;
//
//        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
//            HttpPost postRequest = new HttpPost(jiraURL + "/rest/api/3/issue");
//            postRequest.addHeader("Content-Type", "application/json");
//            String encoding = Base64.getEncoder().encodeToString((jiraUserName + ":" + jiraPassword).getBytes(StandardCharsets.UTF_8));
//            postRequest.setHeader("Authorization", "Basic " + encoding);
//
//            StringEntity params = new StringEntity(jsonPayload.toJSONString());
//            postRequest.setEntity(params);
//
//            try (var response = httpClient.execute(postRequest)) {
//                int statusCode = response.getCode();
//                String responseString = EntityUtils.toString(response.getEntity());
//                LogUtils.info("JIRA Response Status: " + statusCode + ", Body: " + responseString);
//
//                if (statusCode == 200 || statusCode == 201) {
//                    JSONParser parser = new JSONParser();
//                    JSONObject jsonResponse = (JSONObject) parser.parse(responseString);
//                    issueId = (String) jsonResponse.get("key");
//                }
//            }
//        }
//
//        return issueId;
//    }


    private String createPayloadForCreateJiraIssue(Map<String, Object> fields) {
        JSONObject fieldsJson = new JSONObject();

        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            fieldsJson.put(entry.getKey(), entry.getValue());
        }

        JSONObject jsonPayload = new JSONObject();
        jsonPayload.put("fields", fieldsJson);

        LogUtils.info("JSON Payload: " + jsonPayload.toJSONString());
        return jsonPayload.toJSONString();
    }

    public void addAttachmentToJiraIssue(String issueId, String filePath) throws IOException {
        File file = new File(filePath);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost postRequest = new HttpPost(jiraURL + "/rest/api/3/issue/" + issueId + "/attachments");
            String encoding = Base64.getEncoder().encodeToString((jiraUserName + ":" + jiraPassword).getBytes(StandardCharsets.UTF_8));
            postRequest.setHeader("Authorization", "Basic " + encoding);
            postRequest.setHeader("X-Atlassian-Token", "no-check");

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addPart("file", new FileBody(file));
            postRequest.setEntity(builder.build());

            try (var response = httpClient.execute(postRequest)) {
                System.out.println("Response: " + response.getCode() + " - " + response.getReasonPhrase());
                // Additional response handling as needed
            }
        }
    }
    //Method to get the Stack Trace and convert it to String
    public static String getStackTrace(Throwable throwable) {
        if (throwable == null) {
            return "No stack trace available";
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }
    
    //Helper Method for ADF Formatting
    public static JSONObject formatDescriptionToADF(String descriptionText) {
        // Create the text object
        JSONObject textObject = new JSONObject();
        textObject.put("type", "text");
        textObject.put("text", descriptionText);

        // Create the paragraph object
        JSONObject paragraphObject = new JSONObject();
        paragraphObject.put("type", "paragraph");
        JSONArray paragraphContent = new JSONArray();
        paragraphContent.add(textObject);  // Adding text object to paragraph content array
        paragraphObject.put("content", paragraphContent);

        // Create the main description object
        JSONObject descriptionObject = new JSONObject();
        descriptionObject.put("type", "doc");
        descriptionObject.put("version", 1);
        JSONArray contentArray = new JSONArray();
        contentArray.add(paragraphObject);  // Adding paragraph object to main content array
        descriptionObject.put("content", contentArray);

        return descriptionObject;
    }
    
 // Adds a new field to the JSON payload.
//    public static void addField(Map<String, Object> jsonPayload, String fieldName, Object fieldValue) {
//        JSONObject fields = (JSONObject) jsonPayload.get("fields");
//        if (fields == null) {
//            fields = new JSONObject();
//            jsonPayload.put("fields", fields);
//        }
//        fields.put(fieldName, fieldValue);
//    }
//
//    // Removes an existing field from the JSON payload
//    public static void removeField(JSONObject jsonPayload, String fieldName) {
//        JSONObject fields = (JSONObject) jsonPayload.get("fields");
//        if (fields != null) {
//            fields.remove(fieldName);
//        }
//    }
    
    // Convert map to json
    public static JSONObject convertMapToJSON(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            json.put(entry.getKey(), entry.getValue());
        }
        return json;
    }


   

}
