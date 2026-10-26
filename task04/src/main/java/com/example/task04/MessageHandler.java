package com.example.task04;

public interface MessageHandler {
    // логгер будет звать этот метод передавая ему готовую строку
    void handle(String message);
}