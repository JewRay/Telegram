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

// Сервис для планирования отправки уведомлений
@Service
public class NotificationScheduler {

    private final NotificationTaskRepository repository;// Репозиторий для доступа к задачам уведомлений
    private final TelegramBot telegramBot; // Экземпляр Telegram бота для отправки сообщений

    // Конструктор с внедрением зависимостей
    public NotificationScheduler(NotificationTaskRepository repository, TelegramBot telegramBot) {
        this.repository = repository;
        this.telegramBot = telegramBot;
    }

    // Метод, запланированный для выполнения каждую минуту, для отправки уведомлений
    @Scheduled(cron = "0 0/1 * * * *")
    public void sendNotifications() {
        // Получение текущего времени и поиск соответствующих задач уведомлений
        LocalDateTime currentTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        List<NotificationTask> tasks = repository.findByNotificationDateTime(currentTime);
        tasks.forEach(this::sendNotification);// Отправка уведомлений пользователям
    }
    // Вспомогательный метод для отправки уведомления
    private void sendNotification(NotificationTask task) {
        // Получение данных из задачи уведомления и отправка сообщения
        Long chatId = task.getChatId(); // Предполагается, что chatId сохраняется в NotificationTask
        String message = task.getNotificationText();
        sendMessage(chatId, message);
    }
    // Метод для отправки сообщений через Telegram API
    public void sendMessage(Long chatId, String text) {
        // Создание запроса на отправку сообщения
        SendMessage request = new SendMessage(chatId, text)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(false);

        telegramBot.execute(request); // Отправка сообщения через Telegram API
    }
}