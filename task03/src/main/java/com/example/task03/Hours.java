package com.example.task03;

public class Hours implements TimeUnit {

    private final long amount;

    public Hours(long amount) {
        this.amount = amount;
    }

    @Override
    public long toMillis() {
        return amount * 3_600_000L;
    }

    @Override
    public long toSeconds() {
        return amount * 3600L;
    }

    @Override
    public long toMinutes() {
        return amount * 60L;
    }

    @Override
    public long getHours() {
        return amount;
    }
}
