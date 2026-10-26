package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler {
    private final MessageHandler target;
    private final int size;
    private final List<String> buffer = new ArrayList<>();

    public MemoryHandler(MessageHandler target, int size) {
        this.target = target;
        this.size = size;
    }

    @Override
    public void handle(String message) {
        buffer.add(message);
        if (buffer.size() >= size) {
            flush();
        }
    }

    public void flush() {
        for (String message : buffer) {
            target.handle(message);
        }
        buffer.clear();
    }

    @Override
    public void close() {
        flush();
        target.close();
    }
}
