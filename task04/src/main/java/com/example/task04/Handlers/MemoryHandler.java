package com.example.task04.Handlers;
import com.example.task04.MessageHandler;
import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler {
    private final MessageHandler targetHandler;
    private final int capacity;
    private final List<String> buffer;

    public MemoryHandler(MessageHandler targetHandler, int capacity) {
        this.targetHandler = targetHandler;
        this.capacity = capacity;
        this.buffer = new ArrayList<>();
    }

    @Override
    public void handle(String formattedMessage) {
        buffer.add(formattedMessage);
        if (buffer.size() >= capacity) {
            flush();
        }
    }

    // Принудительная отправка накопленных сообщений
    public void flush() {
        if (buffer.isEmpty()) return;
        
        for (String msg : buffer) {
            targetHandler.handle(msg);
        }
        buffer.clear();
    }
}