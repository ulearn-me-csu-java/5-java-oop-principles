package com.example.task03;

/**
 * Класс, в котором собраны методы для работы с TimeUnit
 */
public class TimeUnitUtils {

    /**
     * Секунды -> миллисекунды
     */
    public static Milliseconds toMillis(Seconds seconds) {
        return new Milliseconds(seconds.toMillis());
    }

    /**
     * Миллисекунды -> секунды
     */
    public static Seconds toSeconds(Milliseconds millis) {
        return new Seconds(millis.toSeconds());
    }

    /**
     * Минуты -> миллисекунды
     */
    public static Milliseconds toMillis(Minutes minutes) {
        return new Milliseconds(minutes.toMillis());
    }

    /**
     * Миллисекунды -> минуты
     */
    public static Minutes toMinutes(Milliseconds millis) {
        return new Minutes(millis.toMinutes());
    }

    /**
     * Часы -> миллисекунды
     */
    public static Milliseconds toMillis(Hours hours) {
        return new Milliseconds(hours.toMillis());
    }

    /**
     * Миллисекунды -> часы
     */
    public static Hours toHours(Milliseconds millis) {
        return new Hours(millis.getHours());
    }

    /**
     * Минуты -> секунды
     */
    public static Seconds toSeconds(Minutes minutes) {
        return new Seconds(minutes.toSeconds());
    }

    /**
     * Секунды -> минуты
     */
    public static Minutes toMinutes(Seconds seconds) {
        return new Minutes(seconds.toMinutes());
    }

    /**
     * Часы -> минуты
     */
    public static Minutes toMinutes(Hours hours) {
        return new Minutes(hours.toMinutes());
    }

    /**
     * Минуты -> часы
     */
    public static Hours toHours(Minutes minutes) {
        return new Hours(minutes.getHours());
    }

    /**
     * Часы -> секунды
     */
    public static Seconds toSeconds(Hours hours) {
        return new Seconds(hours.toSeconds());
    }

    /**
     * Секунды -> часы
     */
    public static Hours toHours(Seconds seconds) {
        return new Hours(seconds.getHours());
    }
}
