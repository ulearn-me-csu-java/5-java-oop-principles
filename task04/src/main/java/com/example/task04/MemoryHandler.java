package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler {

    private final MessageHandler handler;
    private final int maxSize;

    private final List<String> messages = new ArrayList<>();

    public MemoryHandler(MessageHandler handler, int maxSize) {
        this.handler = handler;
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
            handler.handle(message);
        }

        messages.clear();
    }
}