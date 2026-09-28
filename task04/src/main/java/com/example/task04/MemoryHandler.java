package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler{

    private final MessageHandler target;
    private final int capacity;
    private final List<String> messages = new ArrayList<>();

    public MemoryHandler(MessageHandler target,int capacity){
        this.target = target;
        this.capacity = capacity;
    }

    @Override
    public void handler(String message){
        messages.add(message);
        if (messages.size() > capacity) sendToTargetHandler();
    }

    private void sendToTargetHandler(){
        for (String message : messages){
            target.handler(message);
        }
        messages.clear();
    }

}