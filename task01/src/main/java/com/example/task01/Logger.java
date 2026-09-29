package com.example.task01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Logger {

    private final String name;
    private Level level = Level.DEBUG;

    private static final Map<String, Logger> instances = new HashMap<>();

    public Logger(String name) {
        this.name = name;
    }

    public static Logger getLogger(String name){
        return instances.computeIfAbsent(name, Logger::new);
    }

    public String getName(){
        return name;
    }

    public Level getLevel(){
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void log(Level messageLevel, String message){
        if (messageLevel.ordinal() < level.ordinal()){
            return;
        }

        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        System.out.printf("[%s] %s %s %s - %s", messageLevel, date, time, name, message);
    }

    public void log(Level messageLevel, String template, Object... args){
        if (messageLevel.ordinal() < level.ordinal()){
            return;
        }

        log(messageLevel, String.format(template, args));
    }

    public void debug(String message){
        log(level.DEBUG, message);
    }

    public void info(String message){
        log(level.INFO, message);
    }

    public void warning(String message){
        log(level.WARNING, message);
    }

    public void error(String message){
        log(level.ERROR, message);
    }

    public void debug(String template, Object... args){
        log(level.DEBUG, template, args);
    }

    public void info(String template, Object... args){
        log(level.INFO, template, args);
    }

    public void warning(String template, Object... args){
        log(level.WARNING, template, args);
    }

    public void error(String template, Object... args){
        log(level.ERROR, template, args);
    }
}