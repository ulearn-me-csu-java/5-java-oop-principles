package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler {
    private List<String> buffer;
    private int bufferSize;
    private MessageHandler target;

    public MemoryHandler(MessageHandler target, int bufferSize) {
        this.bufferSize = bufferSize;
        this.target = target;
        this.buffer = new ArrayList<>();
    }

    @Override
    public void handle(String message) {
        buffer.add(message);
        if (buffer.size() >= bufferSize) {
            flush();
        }
    }

    public void flush() {
        for (String message : buffer) {
            target.handle(message);
        }
        buffer.clear();
    }
}
