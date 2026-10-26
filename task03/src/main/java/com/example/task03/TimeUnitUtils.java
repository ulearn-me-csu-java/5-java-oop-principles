package com.example.task03;

public class TimeUnitUtils {

    public static Milliseconds toMillis(Seconds seconds) {
        return new Milliseconds(seconds.toMillis());
    }

    public static Seconds toSeconds(Milliseconds millis) {
        return new Seconds(millis.toSeconds());
    }

    // --- НОВЫЕ МЕТОДЫ ДЛЯ ЧАСОВ ---

    /**
     * Конвертирует любой интервал в часы
     */
    public static Hours toHours(TimeUnit unit) {
        return new Hours(unit.getHours());
    }

    /**
     * Конвертирует часы в миллисекунды
     */
    public static Milliseconds toMillis(Hours hours) {
        return new Milliseconds(hours.toMillis());
    }

    /**
     * Конвертирует часы в секунды
     */
    public static Seconds toSeconds(Hours hours) {
        return new Seconds(hours.toSeconds());
    }
    /**
     * Конвертирует интервал в секундах в интервал в часах
     */
    public static Hours toHours(Seconds seconds) {
        return new Hours(seconds.getHours());
    }
}