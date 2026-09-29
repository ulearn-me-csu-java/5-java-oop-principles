package com.example.task03;

public class Hours implements TimeUnit {

    private final long amount;

    public Hours(long amount) { this.amount = amount; }

    @Override
    public long getAmount() {
        return amount;
    }

    @Override
    public long toMillis() {
        return amount * 3600000;
    }
}
