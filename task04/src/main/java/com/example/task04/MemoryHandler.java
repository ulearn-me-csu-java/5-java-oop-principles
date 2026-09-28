package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler {
    private final List<String> buffer = new ArrayList<>();
    private final MessageHandler target;
    private final int capacity;

    public MemoryHandler(MessageHandler target, int capacity) {
        this.target = target;
        this.capacity = capacity;
    }

    @Override
    public void handle(String message) {
        buffer.add(message);
        if (buffer.size() >= capacity) {
            flush();
        }
    }
    @Override
    public void flush() {
        for (String line : buffer) {
            target.handle(line);
        }
        buffer.clear();
    }

    @Override
    public void close() {
        flush();
        target.close();
    }
}