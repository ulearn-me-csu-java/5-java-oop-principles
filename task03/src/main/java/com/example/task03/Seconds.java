package com.example.task03;

public class Seconds implements TimeUnit {

    private final long amount;

    public Seconds(long amount) { this.amount = amount; }

    @Override
    public long getAmount() { return amount; }

    @Override
    public long toMillis() { return amount * 1000; }
}
