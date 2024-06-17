package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class NotificationScheduler {

    private final NotificationTaskRepository repository;
    private final TelegramBot telegramBot; // Добавлен TelegramBot для отправки сообщений

    public NotificationScheduler(NotificationTaskRepository repository, TelegramBot telegramBot) {
        this.repository = repository;
        this.telegramBot = telegramBot;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void sendNotifications() {
        LocalDateTime currentTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        List<NotificationTask> tasks = repository.findByNotificationDateTime(currentTime);
        tasks.forEach(this::sendNotification);
    }

    private void sendNotification(NotificationTask task) {
        Long chatId = task.getChatId(); // Предполагается, что chatId сохраняется в NotificationTask
        String message = task.getNotificationText();
        sendMessage(chatId, message);
    }

    public void sendMessage(Long chatId, String text) {
        SendMessage request = new SendMessage(chatId, text)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(false);

        telegramBot.execute(request); // Отправка сообщения через Telegram API
    }
}