package com.qestit.report;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.qestit.constants.FrameworkConstants;
import com.qestit.utils.LogUtils;

public class SlackManager {

    private static final String WEBHOOK_URL = FrameworkConstants.WEBHOOK_URL;

    public static void sendSlackMessage(String messageText) {
        if (FrameworkConstants.SEND_NOTIFICATION_TO_SLACK_CHANNEL.equalsIgnoreCase("yes")) {
            try {
                URL url = new URL(WEBHOOK_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");

                String payload = "{\"text\": \"" + messageText + "\"}";
                try (OutputStream outputStream = connection.getOutputStream()) {
                    outputStream.write(payload.getBytes("UTF-8"));
                    outputStream.flush();
                }

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    LogUtils.info("Sent message to Slack: " + messageText);
                } else {
                    LogUtils.warn("Failed to send message to Slack. Response Code: " + connection.getResponseCode());
                }
            } catch (Exception e) {
                LogUtils.error("Error sending message to Slack: " + e.getMessage());
            }
        }
    }

    public static void sendTestExecutionSummary(int totalTests, String testSuiteName, String reportLink) {
        String messageText = String.format(
            "*🚀 Test Execution Summary: %s*\n" +
            "- *Total Tests*: %d\n" +
            "- *Report Link*: <%s|View Report>",
            testSuiteName, totalTests, reportLink
        );

        sendSlackMessage(messageText);
    }
}
