package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler{
    private final MessageHandler target; //в обработчик будут уходить сообщения после накопления
    private final int bufferSize;
    private final List<String> buffer = new ArrayList<>();

    public MemoryHandler(MessageHandler target, int bufferSize){
        this.target = target;
        this.bufferSize = bufferSize;
    }

    @Override
    public void handle(String message){
        buffer.add(message);
        if (buffer.size() >= bufferSize){
            flush();
        }
    }
    public void flush(){
        for (String msg : buffer){
            target.handle(msg);
        }
        buffer.clear();
    }
}
