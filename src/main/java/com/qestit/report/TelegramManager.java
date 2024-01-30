package com.qestit.report;

import com.qestit.constants.FrameworkConstants;
import com.qestit.helpers.Helpers;
import com.qestit.utils.LogUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import java.io.File;

public class TelegramManager {
    private static String Token = FrameworkConstants.TELEGRAM_TOKEN;
    // https://api.telegram.org/bot6436154193:AAGdI04MlIJgLpi9X2JaIetLuS8H__refRE/getUpdates
    private static String ChatId = FrameworkConstants.TELEGRAM_CHATID;
    private static TelegramBot bot = new TelegramBot(Token);
    private static File input = new File(FrameworkConstants.EXTENT_REPORT_FILE_PATH);

    public static boolean sendFilePath(String filePath) {
        boolean success = false;
        try {
            File input = new File(Helpers.getCurrentDir() + filePath);
            SendDocument request = new SendDocument(ChatId, input).parseMode(ParseMode.HTML).disableNotification(true);
            SendResponse sendResponse = bot.execute(request);
            boolean ok = sendResponse.isOk();
            success = ok;
            if (ok != true) {
                Message message = sendResponse.message();
                LogUtils.warn("Message response from Telegram: " + message);
            }
        } catch (Exception e) {
            LogUtils.error("Error Send Report HTML to Telegram: " + e.getMessage());
        }
        return success;
    }

    public static void sendReportPath() {
        if (FrameworkConstants.SEND_REPORT_TO_TELEGRAM.toLowerCase().trim().equals(FrameworkConstants.YES)) {
            boolean Success = false;
            try {
                SendDocument request = new SendDocument(ChatId, input).parseMode(ParseMode.HTML).disableNotification(true);
                SendResponse sendResponse = bot.execute(request);
                boolean ok = sendResponse.isOk();
                Success = ok;
                if (ok != true) {
                    Message message = sendResponse.message();
                    LogUtils.warn("Message response from Telegram: " + message);
                }
            } catch (Exception e) {
                LogUtils.error("Error Send Report HTML to Telegram: " + e.getMessage());
            }
        }
    }

    // Check here if the sending is successful then delete the file
//    public static boolean deleteReportFile() {
//        boolean success = false;
//        success = sendReportPath();
//        if (success == true) {
//            input.delete();
//        }
//        return success;
//    }

    public static boolean sendMessageText(String messageText) {
        SendMessage request = new SendMessage(ChatId, messageText);
        SendResponse sendResponse = bot.execute(request);
        boolean ok = sendResponse.isOk();
        if (ok == true) {
            LogUtils.info("Send message to Telegram: " + messageText);
        } else {
            LogUtils.info("Send message to Telegram: " + ok);
        }
        return ok;
    }

}
