package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler {
    private final int bufferSize;
    private final MessageHandler targetHandler;
    private final List<String> buffer = new ArrayList<>();

    public MemoryHandler(int bufferSize, MessageHandler targetHandler) {
        this.bufferSize = bufferSize;
        this.targetHandler = targetHandler;
    }

    @Override
    public void handle(String message) {
        buffer.add(message);
        if (buffer.size() >= bufferSize) {
            flush();
        }
    }

    public void flush() {
        for (String msg : buffer) {
            targetHandler.handle(msg);
        }
        buffer.clear();
    }
}
