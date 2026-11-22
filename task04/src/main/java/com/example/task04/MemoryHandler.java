package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler
{
    private final MessageHandler target;
    private final int capacity;
    private final List<String> buffer = new ArrayList<>();

    public MemoryHandler(MessageHandler target, int capacity)
    {
        this.target = target;
        this.capacity = capacity;
    }

    public MemoryHandler(MessageHandler target)
    {
        this(target, 10);
    }

    public MessageHandler getTarget()
    {
        return target;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public int getBufferSize()
    {
        return buffer.size();
    }

    @Override
    public void handle(String message)
    {
        buffer.add(message);
        if (buffer.size() >= capacity)
        {
            flush();
        }
    }

    public void flush()
    {
        for (String message : buffer)
        {
            target.handle(message);
        }
        buffer.clear();
    }
}