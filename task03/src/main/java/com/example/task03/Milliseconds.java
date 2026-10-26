package com.example.task03;

public class Milliseconds implements TimeUnit {

    private final long amount;

    public Milliseconds(long amount) {
        this.amount = amount;
    }

    @Override
    public long toMillis() {
        return amount;
    }

    @Override
    public long toSeconds() {
        // ИСПРАВЛЕНО: делим на 1000.0 для округления (например, 1500 мс = 1.5 сек -> 2 сек)
        return Math.round(amount / 1000.0);
    }

    @Override
    public long toMinutes() {
        // ИСПРАВЛЕНО: убрали умножение на 60 (это был баг), теперь делим на 60000.0
        return Math.round(amount / 60000.0);
    }

    @Override
    public long getHours() {
        // 1 час = 3 600 000 мс
        return Math.round(amount / 3600000.0);
    }
}