package pro.sky.telegrambot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

// Класс для представления задачи уведомления в Telegram боте
@Entity
public class NotificationTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Уникальный идентификатор задачи
    private Long chatId; // Идентификатор чата, в который будет отправлено уведомление
    private String notificationText;// Текст уведомления
    private LocalDateTime notificationDateTime;// Время отправки уведомления

    // Конструктор с параметрами для создания объекта задачи уведомления
    public NotificationTask(Long id, Long chatId, String notificationText, LocalDateTime notificationDateTime) {
        this.id = id;
        this.chatId = chatId;
        this.notificationText = notificationText;
        this.notificationDateTime = notificationDateTime;
    }
    // Конструктор без параметров
    public NotificationTask() {

    }
    // Геттеры и сеттеры для доступа и обновления свойств объекта
    public Long getId() {
        return id;
    }

    public Long getChatId() {
        return chatId;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public LocalDateTime getNotificationDateTime() {
        return notificationDateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public void setNotificationDateTime(LocalDateTime notificationDateTime) {
        this.notificationDateTime = notificationDateTime;
    }

    // Переопределенный метод toString для представления объекта задачи уведомления в виде строки
    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", notificationText='" + notificationText + '\'' +
                ", notificationDateTime=" + notificationDateTime +
                '}';
    }
}
