package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler {

    private static class Entry {
        final Logger.Level level;
        final String message;
        Entry(Logger.Level level, String message) {
            this.level = level;
            this.message = message;
        }
    }

    private final MessageHandler target;
    private final int capacity;
    private final List<Entry> buffer = new ArrayList<>();

    public MemoryHandler(MessageHandler target, int capacity) {
        this.target = target;
        this.capacity = capacity;
    }

    @Override
    public synchronized void handle(Logger.Level level, String formattedMessage) {
        buffer.add(new Entry(level, formattedMessage));
        if (buffer.size() >= capacity) {
            flush();
        }
    }

    public synchronized void flush() {
        for (Entry entry : buffer) {
            target.handle(entry.level, entry.message);
        }
        buffer.clear();
    }

    public synchronized int bufferedCount() {
        return buffer.size();
    }
}