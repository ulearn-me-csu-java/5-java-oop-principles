package com.example.task03;

public class TimeUnitUtils {

    public static Milliseconds toMillis(TimeUnit unit){ return new Milliseconds(unit.toMillis()); }
    public static Seconds toSeconds(TimeUnit unit){ return new Seconds(unit.getSeconds()); }
    public static Hours toHours(TimeUnit unit){ return new Hours(unit.getHours()); }
    public static Minutes toMinutes(TimeUnit unit){ return new Minutes(unit.getMinutes()); }

    public static Seconds toSeconds(Hours hours) { return new Seconds(hours.getSeconds());}
    public static Seconds toSeconds(Minutes minutes) { return new Seconds(minutes.getSeconds());}
    public static Seconds toSeconds(Milliseconds milliseconds) { return new Seconds(milliseconds.getSeconds());}

    public static Hours toHours(Seconds seconds) { return new Hours(seconds.getHours());}
    public static Hours toHours(Minutes minutes) { return new Hours(minutes.getHours());}
    public static Hours toHours(Milliseconds milliseconds) { return new Hours(milliseconds.getHours());}

    public static Minutes toMinutes(Hours hours) { return new Minutes(hours.getMinutes());}
    public static Minutes toMinutes(Seconds seconds) { return new Minutes(seconds.getMinutes());}
    public static Minutes toMinutes(Milliseconds milliseconds) { return new Minutes(milliseconds.getMinutes());}

}
