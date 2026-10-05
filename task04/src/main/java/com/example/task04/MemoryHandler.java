package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler{
    private final MessageHandler target;
    private final int sizeMax;
    private final List<String> messages = new ArrayList<>();

    public MemoryHandler(MessageHandler target,int sizeMax){
        this.target = target;
        this.sizeMax = sizeMax;
    }

    @Override
    public void handler(String message){
        messages.add(message);
        if (messages.size() > sizeMax) flush();
    }

    private void flush(){
        for (String message : messages){
            target.handler(message);
        }
        messages.clear();
    }
}