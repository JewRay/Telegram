package pro.sky.telegrambot.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.time.LocalDateTime;

@Entity
public class NotificationTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private String notificationText;
    private LocalDateTime notificationDateTime;

    public NotificationTask(Long id, Long chatId, String notificationText, LocalDateTime notificationDateTime) {
        this.id = id;
        this.chatId = chatId;
        this.notificationText = notificationText;
        this.notificationDateTime = notificationDateTime;
    }

    public NotificationTask() {

    }

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
