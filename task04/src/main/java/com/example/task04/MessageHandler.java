package com.example.task04;

/**
 * Обработчик сообщений логгера
 */
public interface MessageHandler {

    /**
     * Обрабатывает сообщение логгера
     *
     * @param message сообщение
     */
    void handle(String message);
}
