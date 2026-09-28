package com.example.task04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryHandler implements MessageHandler {

    private final List<String> buffer = new ArrayList<>();
    private int maxSize;
    private MessageHandler targetHandler;

    public MemoryHandler() {
        this(10, new ConsoleHandler());
    }

    public MemoryHandler(int maxSize) {
        this(maxSize, new ConsoleHandler());
    }

    public MemoryHandler(MessageHandler targetHandler) {
        this(10, targetHandler);
    }

    public MemoryHandler(int maxSize, MessageHandler targetHandler) {
        this.maxSize = maxSize;
        this.targetHandler = targetHandler;
    }

    public MemoryHandler(MessageHandler targetHandler, int maxSize) {
        this(maxSize, targetHandler);
    }

    @Override
    public void handle(String message) {
        buffer.add(message);
        if (buffer.size() >= maxSize) {
            flush();
        }
    }

    public void flush() {
        if (targetHandler != null) {
            for (String message : buffer) {
                targetHandler.handle(message);
            }
        }
        buffer.clear();
    }

    public void dump() {
        flush();
    }

    public void send() {
        flush();
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public MessageHandler getTargetHandler() {
        return targetHandler;
    }

    public void setTargetHandler(MessageHandler targetHandler) {
        this.targetHandler = targetHandler;
    }

    public int getBufferSize() {
        return buffer.size();
    }

    public int getSize() {
        return buffer.size();
    }

    public List<String> getMessages() {
        return Collections.unmodifiableList(buffer);
    }

    public void clear() {
        buffer.clear();
    }
}
