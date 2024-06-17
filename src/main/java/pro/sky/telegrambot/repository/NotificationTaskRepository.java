package pro.sky.telegrambot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.entity.NotificationTask;

import java.time.LocalDateTime;
import java.util.List;

// Репозиторий для работы с задачами уведомлений в базе данных
@Repository
public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Long> {
    // Метод для поиска задач уведомлений по времени уведомления
    List<NotificationTask> findByNotificationDateTime(LocalDateTime dateTime);
}