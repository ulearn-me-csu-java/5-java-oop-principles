package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler {

    private final int bufferSize;
    private final MessageHandler targetHandler;
    private final List<String> messages = new ArrayList<>();

    public MemoryHandler(int bufferSize, MessageHandler targetHandler) {
        this.bufferSize = bufferSize;
        this.targetHandler = targetHandler;
    }

    @Override
    public void log(String s) {
        messages.add(s);
        if (messages.size() >= bufferSize) {
            flush();
        }
    }

    public void flush() {
        for (String msg : messages) {
            targetHandler.log(msg);
        }
        messages.clear();
    }
}

