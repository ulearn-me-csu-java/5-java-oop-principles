package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler {

    private final MessageHandler target;
    private final int maxSize;
    private final List<String> messages = new ArrayList<>();

    public MemoryHandler(MessageHandler target, int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Размер буфера должен быть больше нуля");
        }

        this.target = target;
        this.maxSize = maxSize;
    }

    @Override
    public void handle(String message) {
        messages.add(message);

        if (messages.size() >= maxSize) {
            flush();
        }
    }

    public void flush() {
        for (String message : messages) {
            target.handle(message);
        }

        messages.clear();
    }
}