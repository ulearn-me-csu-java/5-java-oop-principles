package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler {
    private final MessageHandler handler;
    private final int limit;
    private final List<String> messages = new ArrayList<>();

    public MemoryHandler(MessageHandler handler, int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException(
                    "Limit must be greater than zero");
        }

        this.handler = handler;
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
        List<String> savedMessages = new ArrayList<>(messages);
        messages.clear();

        for (String message : savedMessages) {
            handler.handle(message);
        }
    }

    public int getSize() {
        return messages.size();
    }
}