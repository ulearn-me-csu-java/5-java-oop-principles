package com.example.task03;

public class Minutes implements TimeUnit {

    private final long amount;

    public Minutes(long amount) {
        this.amount = amount;
    }

    @Override
    public long getAmount() { return amount; }

    @Override
    public long toMillis() { return amount * 60000; }
}
