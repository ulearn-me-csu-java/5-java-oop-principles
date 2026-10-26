package com.example.task03;

public class Seconds implements TimeUnit {

    private final long amount;

    public Seconds(long amount) {
        this.amount = amount;
    }

    @Override
    public long toMillis() {
        return amount * 1000;
    }

    @Override
    public long toSeconds() {
        return amount;
    }

    @Override
    public long toMinutes() {
        // ИСПРАВЛЕНО: делим на 60.0, чтобы получить дробь, и только потом округляем
        return Math.round(amount / 60.0);
    }

    @Override
    public long getHours() {
        // 1 час = 3600 секунд
        return Math.round(amount / 3600.0);
    }
}