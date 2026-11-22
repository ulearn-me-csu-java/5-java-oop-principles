package com.example.task03;

/**
 * Класс, в котором собраны методы для работы с {@link TimeUnit}
 */
public class TimeUnitUtils
{
    /**
     * Конвертирует интервал в секундах в интервал в миллисекундах
     *
     * @param seconds интервал в секундах
     * @return интервал в миллисекундах
     */
    public static Milliseconds toMillis(Seconds seconds)
    {
        return new Milliseconds(seconds.toMillis());
    }

    /**
     * Конвертирует интервал в часах в интервал в миллисекундах
     */
    public static Milliseconds toMillis(Hours hours)
    {
        return new Milliseconds(hours.toMillis());
    }

    /**
     * Конвертирует интервал в миллисекундах в интервал в секундах
     *
     * @param millis интервал в миллисекундах
     * @return интервал в секундах
     */
    public static Seconds toSeconds(Milliseconds millis)
    {
        return new Seconds(millis.toSeconds());
    }

    /**
     * Конвертирует интервал в часах в интервал в секундах
     */
    public static Seconds toSeconds(Hours hours)
    {
        return new Seconds(hours.toSeconds());
    }

    /**
     * Конвертирует интервал в часах в интервал в минутах
     */
    public static Minutes toMinutes(Hours hours)
    {
        return new Minutes(hours.toMinutes());
    }

    /**
     * Конвертирует интервал в миллисекундах в интервал в часах
     */
    public static Hours toHours(Milliseconds millis)
    {
        return new Hours(millis.getHours());
    }

    /**
     * Конвертирует интервал в секундах в интервал в часах
     */
    public static Hours toHours(Seconds seconds)
    {
        return new Hours(seconds.getHours());
    }

    /**
     * Конвертирует интервал в минутах в интервал в часах
     */
    public static Hours toHours(Minutes minutes)
    {
        return new Hours(minutes.getHours());
    }
}
