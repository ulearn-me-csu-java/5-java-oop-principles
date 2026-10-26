package com.example.task03;

/**
 * Интервал в часах
 */
public class Hours implements TimeUnit {

    private final long amount;

    public Hours(long amount) {
        this.amount = amount;
    }

    @Override
    public long toMillis() {
        return amount * 3600 * 1000; // 1 час = 3600 сек * 1000 мс
    }

    @Override
    public long toSeconds() {
        return amount * 3600; // 1 час = 3600 сек
    }

    @Override
    public long toMinutes() {
        return amount * 60; // 1 час = 60 мин
    }

    @Override
    public long getHours() {
        return amount; // это уже часы, ничего переводить не надо
    }
}