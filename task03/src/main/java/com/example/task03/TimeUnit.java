package com.example.task03;

/**
 * Интерфейс для интервалов времени в определенных единицах
 */
public interface TimeUnit {

    long getAmount(); // Возвращает значение
    long toMillis(); // Переводит в миллисекунды

    default long toSeconds() {
        return toMillis() / 1000;
    }

    default long toHours() {
        return toMillis() / 3600000;
    }
}
