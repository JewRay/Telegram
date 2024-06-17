package pro.sky.telegrambot.exception;


public class TelegramApiException extends Exception {

    public TelegramApiException(String message) {
        super(message);
    }

    public TelegramApiException(String message, Throwable cause) {
        super(message, cause);
    }

}