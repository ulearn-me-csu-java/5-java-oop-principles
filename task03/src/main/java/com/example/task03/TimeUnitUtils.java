package com.example.task03;

/**
 * Класс, в котором собраны методы для работы с {@link TimeUnit}
 */
public class TimeUnitUtils {
    //Конвертирует интервал в миллисекундах в интервал в миллисекундах
    public static Milliseconds toMillis(Milliseconds millis) {
        return new Milliseconds(millis.toMillis());
    }

    //Конвертирует интервал в секундах в интервал в миллисекундах
    public static Milliseconds toMillis(Seconds seconds) {
        return new Milliseconds(seconds.toMillis());
    }

    //Конвертирует интервал в минутах в интервал в миллисекундах
    public static Milliseconds toMillis(Minutes minutes) {
        return new Milliseconds(minutes.toMillis());
    }

    //Конвертирует интервал в часах в интервал в миллисекундах
    public static Milliseconds toMillis(Hours hours) {
        return new Milliseconds(hours.toMillis());
    }


    //Конвертирует интервал в миллисекундах в интервал в секундах
    public static Seconds toSeconds(Milliseconds millis) {
        return new Seconds(millis.toSeconds());
    }

    //Конвертирует интервал в секундах в интервал в секундах
    public static Seconds toSeconds(Seconds seconds) {
        return new Seconds(seconds.toSeconds());
    }

    //Конвертирует интервал в минутах в интервал в секундах
    public static Seconds toSeconds(Minutes minutes) {
        return new Seconds((minutes.toSeconds()));
    }

    //Конвертирует интервал в часах в интервал в секундах
    public static Seconds toSeconds(Hours hours) {
        return new Seconds((hours.toSeconds()));
    }

    //Конвертирует интервал в миллисекундах в интервал в минутах
    public static Minutes toMinutes(Milliseconds mills) {
        return new Minutes(mills.toMinutes());
    }

    //Конвертирует интервал в секундах в интервал в минутах
    public static Minutes toMinutes(Seconds seconds) {
        return new Minutes(seconds.toMinutes());
    }

    //Конвертирует интервал в минутах в интервал в минутах
    public static Minutes toMinutes(Minutes minutes) {
        return new Minutes(minutes.toMinutes());
    }

    //Конвертирует интервал в часах в интервал в минутах
    public static Minutes toMinutes(Hours hours) {
        return new Minutes(hours.toMinutes());
    }

    //Конвертирует интервал в миллисекундах в интервал в часах
    public static Hours toHours(Milliseconds mills) {
        return new Hours(mills.toHours());
    }

    //Конвертирует интервал в секундах в интервал в часах
    public static Hours toHours(Seconds seconds) {
        return new Hours(seconds.toHours());
    }

    //Конвертирует интервал в миллисекундах в интервал в часах
    public static Hours toHours(Minutes minutes) {
        return new Hours(minutes.toHours());
    }

    //Конвертирует интервал в миллисекундах в интервал в часах
    public static Hours toHours(Hours hours) {
        return new Hours(hours.toHours());
    }
}
