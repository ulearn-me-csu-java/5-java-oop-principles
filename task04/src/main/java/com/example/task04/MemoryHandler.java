package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler {
    private final MessageHandler targetHandler;
    private final int bufferSize;
    private final List<String> buffer = new ArrayList<>();

    public MemoryHandler(MessageHandler targetHandler, int bufferSize) {
        this.targetHandler = targetHandler;
        this.bufferSize = bufferSize;
    }

    @Override
    public void handle(String message) {
        buffer.add(message);
        if (buffer.size() >= bufferSize) {
            flush();
        }
    }

    // метод для отправки накопленных сообщений
    public void flush() {
        for (String msg : buffer) {
            targetHandler.handle(msg);
        }
        buffer.clear();
    }
}