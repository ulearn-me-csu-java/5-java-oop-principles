package com.example.task04;

public interface MessageHandler {

    void handle(Logger.Level level, String formattedMessage);
}
