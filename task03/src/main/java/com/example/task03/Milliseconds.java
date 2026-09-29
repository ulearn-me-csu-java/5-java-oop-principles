package com.example.task03;

public class Milliseconds implements TimeUnit {

    private final long amount;

    public Milliseconds(long amount) {
        this.amount = amount;
    }

    @Override
    public long getAmount() {
        return amount;
    }

    @Override
    public long toMillis() {
        return amount;
    }

}
