package com.example.task01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Logger {
    public enum Level {
        DEBUG, INFO, WARNING, ERROR
    }

    private static final Map<String, Logger> CACHE = new HashMap<>();
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
    
    private final String name;
    private Level level;

    private Logger(String name) {
        this(name, Level.DEBUG);
    }

    private Logger(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public static Logger getLogger(String name) {
        return CACHE.computeIfAbsent(name, Logger::new);
    }

    public String getName() {
        return name;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public void log(Level level, String message) {
        if (level.ordinal() < this.level.ordinal()) {
            return;
        }
        
        System.out.printf("[%s] %s %s - %s%n",
                level, LocalDateTime.now().format(FMT), name, message);
    }

    public void log(Level level, String template, Object... args) {
        if (level.ordinal() < this.level.ordinal()) {
            return;
        }
        
        String msg = String.format(template, args);
        System.out.printf("[%s] %s %s - %s%n",
                level, LocalDateTime.now().format(FMT), name, msg);
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