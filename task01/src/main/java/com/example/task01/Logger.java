package com.example.task01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Logger{
    public enum Level{
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }

    private final String name;
    private static final Map<String, Logger> loggers = new HashMap<>();
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    private Level level = Level.DEBUG;
    private Logger (String name){
        this.name = name;
    }
    public static Logger getLogger(String name) {
        if (!loggers.containsKey(name)) {loggers.put(name, new Logger(name));}
        return loggers.get(name);
    }

    public String getName(){
        return this.name;
    }

    public void setLevel(Level level){
        this.level = level;
    }
    public Level getLevel(){
        return this.level;
    }

    public void log(Level level, String message){
        if (level.ordinal() < this.level.ordinal()) {return;}
        LocalDateTime now = LocalDateTime.now();
        System.out.println("["+level+"] " + now.format(dateFormat) + " " + now.format(timeFormat) + " " + name + " - " + message);
    }

    public void log(Level level, String template, Object... args) {
        log(level, String.format(template, args));
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void debug(String template, Object... args) {
        log(Level.DEBUG, template, args);
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