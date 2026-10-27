package com.example.task04;

import java.util.ArrayList;

public class MemoryHandler implements MessageHandler{
    private ArrayList<String> buffer = new ArrayList<String>();
    private int sizeMaxLimit = Integer.MAX_VALUE;
    private MessageHandler handler;

    public MemoryHandler(MessageHandler handler) {
        if (handler.getClass().equals(this.getClass()))
            throw new IllegalArgumentException("Обработчик не может вызывать сам себя!");

        this.handler = handler;
    }

    public void setHandler(MessageHandler handler) {
        this.handler = handler;
    }

    public void setSizeMaxLimit(int sizeMaxLimit) {
        this.sizeMaxLimit = sizeMaxLimit;

        if (sizeMaxLimit <= buffer.size()) {
            getLogs(sizeMaxLimit);
        }
    }

    public void getLogs() {
        for (String message : buffer) {
            handler.handle(message);
        }
        buffer.clear();
    }

    private void getLogs(int limit) {
        for (int i = 0; i < limit; i++) {
            handler.handle(buffer.get(i));
        }

        buffer = new ArrayList<>(buffer.subList(limit, buffer.size()));
    }

    @Override
    public void handle(String message) {
        buffer.add(message);
        if (buffer.size() >= sizeMaxLimit) {
            for (String m : buffer) {
                handler.handle(m);
            }
            buffer.clear();
        }
    }
}