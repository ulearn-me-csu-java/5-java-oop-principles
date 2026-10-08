package com.example.task03;

/**
 * Класс, в котором собраны методы для работы с {@link TimeUnit}
 */
public class TimeUnitUtils {
    /**
     * Конвертирует интервал в секундах в интервал в миллисекундах
     *
     * @param seconds интервал в секундах
     * @return интервал в миллисекундах
     */
    public static Milliseconds getMilliseconds(Seconds seconds) {
        return new Milliseconds(seconds.getMilliseconds());
    }

    /**
     * Конвертирует интервал в миллисекундах в интервал в секундах
     *
     * @param millis интервал в миллисекундах
     * @return интервал в секундах
     */
    public static Seconds getSeconds(Milliseconds milliseconds) {
        return new Seconds(milliseconds.getSeconds());
    }

    public static Hours getHours(Seconds seconds) {
        return new Hours(seconds.getHours());
    }

    public static Hours getHours(Milliseconds milliseconds) {
        return new Hours(milliseconds.getHours());
    }

    public static Seconds getSeconds(Hours hours) {
        return new Seconds(hours.getSeconds());
    }

    public static Milliseconds getMilliseconds(Hours hours) {
        return new Milliseconds(hours.getMilliseconds());
    }
}