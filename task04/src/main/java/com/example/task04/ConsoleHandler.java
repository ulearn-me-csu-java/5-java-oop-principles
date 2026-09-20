package com.example.task04;

public class ConsoleHandler implements MessageHandler {

    @Override
    public void handle(Logger.Level level, String formattedMessage){

        System.out.println(formattedMessage);
    }
}
