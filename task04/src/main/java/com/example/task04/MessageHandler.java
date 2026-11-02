package com.example.task04;

/** Конечный обработчик уже отформатированных сообщений логгера. */
public interface MessageHandler {
    void handle(String message);

    /** Принудительно отправить накопленное (если обработчик что-то накапливает). */
    default void flush() {
    }

    /** Освободить ресурсы. */
    default void close() {
        flush();
    }
}
