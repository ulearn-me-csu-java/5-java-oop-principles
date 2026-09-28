package com.example.task03;

/**
 * Интервал в минутах
 */
public class Minutes implements TimeUnit {

    private final long amount;

    public Minutes(long amount) {
        this.amount = amount;
    }

    @Override
    public long toMillis() {
        return amount * 60_000L;
    }

    @Override
    public long toSeconds() {
        return amount * 60L;
    }

    @Override
    public long toMinutes() {
        return amount;
    }

    @Override
    public long getHours() {
        return Math.round(amount / 60.0);
    }
}
