package pro.sky.telegrambot.exception;

// Класс исключения, специфичного для Telegram API
public class TelegramApiException extends Exception {

    // Конструктор с сообщением об ошибке
    public TelegramApiException(String message) {
        super(message);
    }

    // Конструктор с сообщением об ошибке и причиной
    public TelegramApiException(String message, Throwable cause) {
        super(message, cause);
    }
}