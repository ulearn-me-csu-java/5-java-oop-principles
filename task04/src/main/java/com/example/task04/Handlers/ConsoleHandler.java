package com.example.task04.Handlers;

import com.example.task04.MessageHandler;

public class ConsoleHandler implements MessageHandler {

    @Override
    public void handle(String formattedMessage) {
        System.out.println(formattedMessage);
    }
}
