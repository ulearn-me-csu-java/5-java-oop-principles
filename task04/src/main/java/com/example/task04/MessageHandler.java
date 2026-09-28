package com.example.task04;

@FunctionalInterface
public interface MessageHandler {

    void handle(String message);

    default void log(String message) {
        handle(message);
    }
}
