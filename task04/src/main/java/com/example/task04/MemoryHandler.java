package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler {
    private final MessageHandler targetHandler; // Куда отправлять
    private final int capacity;                 // Сколько копить
    private final List<String> buffer = new ArrayList<>();

    public MemoryHandler(MessageHandler targetHandler, int capacity) {
        this.targetHandler = targetHandler;
        this.capacity = capacity;
    }

    @Override
    public void handle(String formattedMessage) {
        buffer.add(formattedMessage);
        if (buffer.size() >= capacity) {
            flush(); // Если переполнилось, отправляем
        }
    }

    // Принудительная отправка накопленного
    public void flush() {
        for (String msg : buffer) {
            targetHandler.handle(msg);
        }
        buffer.clear();
    }
}
