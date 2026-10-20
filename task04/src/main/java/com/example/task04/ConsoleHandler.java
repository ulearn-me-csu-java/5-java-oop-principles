package com.example.task04;

public class ConsoleHandler implements MessageHandler{
    @Override
    public void handle(LogRecord record){
        System.out.println(record.format());
    }
}
