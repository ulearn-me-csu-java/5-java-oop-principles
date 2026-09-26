package com.example.task03;

public class TimeUnitUtils {

    public static Milliseconds toMillis(TimeUnit unit){
        return new Milliseconds(unit.toMillis());
    }

    public static Seconds toSeconds(TimeUnit unit){
        return new Seconds(unit.getSeconds());
    }

    public static Hours toHours(TimeUnit unit){
        return new Hours(unit.getHours());
    }

    public static Minutes toMinutes(TimeUnit unit){
        return new Minutes(unit.getMinutes());
    }
}
