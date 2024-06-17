package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;
import pro.sky.telegrambot.util.NotificationParser;

@Service
public class NotificationService {

    private final NotificationTaskRepository repository;

    public NotificationService(NotificationTaskRepository repository) {
        this.repository = repository;
    }

    public void saveNotification(Long chatId, String message) {
        NotificationTask task = NotificationParser.parseNotification(message);
        task.setChatId(chatId); // Установка chatId для объекта task
        repository.save(task);
    }
}