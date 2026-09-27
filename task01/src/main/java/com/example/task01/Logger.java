package com.example.task01;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Logger {
    private String name;
    private String message;
    private Level level = Level.DEBUG;
    private LocalDate date;
    private LocalTime time;
    private static Map<String,Logger> obj = new HashMap<>();
    private enum Level{
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }

    private Logger(String name){
        this.name = name;
    }

    public String getName() {return name;}

    public static Logger getLogger(String name){
        if (!obj.containsKey(name)) {
            obj.put(name,new Logger(name));
        }
        return obj.get(name);
    }
    public void setLevel(Level level){
        this.level = level;
    }
    public Level getLevel(){
        return this.level;
    }

    private void log(Level level,String message){
        if (level.ordinal() < this.level.ordinal()) return;
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        System.out.println("[" + level + "]" + " "+ date + " " + time + " " + name + " " + "-" + " " + message);
    }
    private void log(Level level, String message, Object... args){
        log(level,String.format(message,args));
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void debug(String template, Object... args) {
        log(Level.DEBUG,template, args);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void info(String template, Object... args) {
        log(Level.INFO, template, args);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void warning(String template, Object... args) {
        log(Level.WARNING, template, args);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }

    public void error(String template, Object... args) {
        log(Level.ERROR, template, args);

    }
}
