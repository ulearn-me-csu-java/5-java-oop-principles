package com.example.task04;

import java.util.ArrayList;
import java.util.List;

/**
 * Прокси-обработчик: копит сообщения в памяти и отправляет "порцией"
 * в целевой обработчик при вызове flush() или при достижении capacity.
 */
public class MemoryHandler implements MessageHandler {
    private final MessageHandler target;
    private final int capacity;
    private final List<String> buffer = new ArrayList<>();

    public MemoryHandler(MessageHandler target, int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.target = target;
        this.capacity = capacity;
    }

    @Override
    public synchronized void handle(String message) {
        buffer.add(message);
        if (buffer.size() >= capacity) {
            flush();
        }
    }

    @Override
    public synchronized void flush() {
        for (String message : buffer) {
            target.handle(message);
        }
        buffer.clear();
        target.flush();
    }

    @Override
    public synchronized void close() {
        flush();
        target.close();
    }
}
