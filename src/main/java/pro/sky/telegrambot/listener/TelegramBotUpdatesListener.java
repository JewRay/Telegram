package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exception.TelegramApiException;
import pro.sky.telegrambot.service.NotificationService;


import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private NotificationService notificationService; // Используется NotificationService для сохранения уведомлений

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            if (update.message() != null && update.message().text() != null) {
                String messageText = update.message().text();
                Long chatId = update.message().chat().id();

                if (messageText.equals("/start")) {
                    String welcomeMessage = "Привет! Я бот, который поможет тебе не забыть о важных делах.";
                    sendMessage(chatId, welcomeMessage);
                } else {
                    try {
                        notificationService.saveNotification(chatId, messageText); // Передача chatId и messageText
                        sendMessage(chatId, "Уведомление установлено!");
                    } catch (IllegalArgumentException e) {
                        sendMessage(chatId, "Неверный формат сообщения. Пожалуйста, используйте формат 'дд.мм.гггг чч:мм Текст уведомления'.");
                    }
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
    public void sendMessage(Long chatId, String text) {
        SendMessage request = new SendMessage(chatId, text)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(false);

        telegramBot.execute(request);
    }

}
