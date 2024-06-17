package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;
import pro.sky.telegrambot.util.NotificationParser;

// Сервис для сохранения уведомлений
@Service
public class NotificationService {

    private final NotificationTaskRepository repository;// Репозиторий для доступа к задачам уведомлений

    // Конструктор с внедрением зависимостей
    public NotificationService(NotificationTaskRepository repository) {
        this.repository = repository;
    }
    // Метод для сохранения уведомления в базе данных
    public void saveNotification(Long chatId, String message) {
        // Парсинг сообщения и создание задачи уведомления
        NotificationTask task = NotificationParser.parseNotification(message);
        task.setChatId(chatId); // Установка chatId для объекта task
        repository.save(task);// Сохранение задачи уведомления в базе данных
    }
}