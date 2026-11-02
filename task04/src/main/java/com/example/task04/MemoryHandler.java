package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler {

    private final MessageHandler targetHandler;
    private final int limit;
    private final List<String> messages = new ArrayList<>();

    public MemoryHandler(MessageHandler targetHandler, int limit) {
        this.targetHandler = targetHandler;
        this.limit = limit;
    }

    @Override
    public void handle(String message) {
        messages.add(message);

        if (messages.size() >= limit) {
            flush();
        }
    }

    public void flush() {
        for (String message : messages) {
            targetHandler.handle(message);
        }

        messages.clear();
    }
}