package com.example.task03;

/**
 * Интерфейс для интервалов времени в определенных единицах
 */
public interface TimeUnit {

    long getAmount(); // Возвращает значение
    long toMillis(); // Переводит в миллисекунды

    default long getSeconds() {
        return (toMillis()+500) / 1000;
    }

    default long getHours() {
        return (toMillis()+1800000) / 3600000;
    }

    default long getMinutes() { return  (toMillis()+30000) / 60000; }
}
