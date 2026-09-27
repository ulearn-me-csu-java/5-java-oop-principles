package com.example.task03;

/**
 * Класс, в котором собраны методы для работы с {@link TimeUnit}
 */
public class TimeUnitUtils {

    // ---- в Milliseconds ----

    /**
     * Конвертирует интервал в секундах в интервал в миллисекундах
     */
    public static Milliseconds toMillis(Seconds seconds) {
        return new Milliseconds(seconds.toMillis());
    }

    /**
     * Конвертирует интервал в минутах в интервал в миллисекундах
     */
    public static Milliseconds toMillis(Minutes minutes) {
        return new Milliseconds(minutes.toMillis());
    }

    /**
     * Конвертирует интервал в часах в интервал в миллисекундах
     */
    public static Milliseconds toMillis(Hours hours) {
        return new Milliseconds(hours.toMillis());
    }

    // ---- в Seconds ----

    /**
     * Конвертирует интервал в миллисекундах в интервал в секундах
     */
    public static Seconds toSeconds(Milliseconds millis) {
        return new Seconds(millis.toSeconds());
    }

    /**
     * Конвертирует интервал в минутах в интервал в секундах
     */
    public static Seconds toSeconds(Minutes minutes) {
        return new Seconds(minutes.toSeconds());
    }

    /**
     * Конвертирует интервал в часах в интервал в секундах
     */
    public static Seconds toSeconds(Hours hours) {
        return new Seconds(hours.toSeconds());
    }

    // ---- в Minutes ----

    /**
     * Конвертирует интервал в миллисекундах в интервал в минутах
     */
    public static Minutes toMinutes(Milliseconds millis) {
        return new Minutes(millis.toMinutes());
    }

    /**
     * Конвертирует интервал в секундах в интервал в минутах
     */
    public static Minutes toMinutes(Seconds seconds) {
        return new Minutes(seconds.toMinutes());
    }

    /**
     * Конвертирует интервал в часах в интервал в минутах
     */
    public static Minutes toMinutes(Hours hours) {
        return new Minutes(hours.toMinutes());
    }

    // ---- в Hours ----

    /**
     * Конвертирует интервал в миллисекундах в интервал в часах
     */
    public static Hours toHours(Milliseconds millis) {
        return new Hours(millis.toHours());
    }

    /**
     * Конвертирует интервал в секундах в интервал в часах
     */
    public static Hours toHours(Seconds seconds) {
        return new Hours(seconds.toHours());
    }

    /**
     * Конвертирует интервал в минутах в интервал в часах
     */
    public static Hours toHours(Minutes minutes) {
        return new Hours(minutes.toHours());
    }
}