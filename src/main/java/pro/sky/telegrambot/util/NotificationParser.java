package pro.sky.telegrambot.util;

import pro.sky.telegrambot.entity.NotificationTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NotificationParser {

    private static final Pattern pattern = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");

    public static NotificationTask parseNotification(String message) {
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            String dateTimeString = matcher.group(1);
            String notificationText = matcher.group(3).trim();

            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            return new NotificationTask(null, null, notificationText, dateTime);
        }
        throw new IllegalArgumentException("Message does not match the expected format");
    }
}