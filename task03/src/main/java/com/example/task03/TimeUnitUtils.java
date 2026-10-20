package com.example.task03;

/**
 * Класс, в котором собраны методы для работы с {@link TimeUnit}
 */
public class TimeUnitUtils {


    public static Milliseconds toMillis(Seconds seconds) {
        return new Milliseconds(seconds.toMillis());
    }

    public static Seconds toSeconds(Milliseconds millis) {
        return new Seconds(millis.toSeconds());
    }

    public static Hours toHours(Milliseconds millis) {
        return new Hours(millis.getHours());
    }

    public static Hours toHours(Seconds seconds) {
        return new Hours(seconds.getHours());
    }

    public static Hours toHours(Minutes minutes) {
        return new Hours(minutes.getHours());
    }


    public static Milliseconds toMillis(Hours hours) {
        return new Milliseconds(hours.toMillis());
    }


    public static Seconds toSeconds(Hours hours) {
        return new Seconds(hours.toSeconds());
    }


    public static Minutes toMinutes(Hours hours) {
        return new Minutes(hours.toMinutes());
    }
}
