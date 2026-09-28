package com.example.task01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Logger {

    public enum Level {
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }

    private static final Map<String, Logger> instances = new HashMap<>();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final String name;
    private Level level = Level.DEBUG;

    private Logger(String name) {
        this.name = name;
    }

    public static Logger getLogger(String name) {
        return instances.computeIfAbsent(name, Logger::new);
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

    private void print(Level level, String message) {
        if (level.ordinal() < this.level.ordinal()) {
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        System.out.println("[" + level + "] " + now.format(DATE_FORMAT) + " " + now.format(TIME_FORMAT) + " " + name + " - " + message);
    }

    public void debug(String message) {
        print(Level.DEBUG, message);
    }

    public void debug(String format, Object... args) {
        print(Level.DEBUG, String.format(format, args));
    }

    public void info(String message) {
        print(Level.INFO, message);
    }

    public void info(String format, Object... args) {
        print(Level.INFO, String.format(format, args));
    }

    public void warning(String message) {
        print(Level.WARNING, message);
    }

    public void warning(String format, Object... args) {
        print(Level.WARNING, String.format(format, args));
    }

    public void error(String message) {
        print(Level.ERROR, message);
    }

    public void error(String format, Object... args) {
        print(Level.ERROR, String.format(format, args));
    }

    public void log(Level level, String message) {
        print(level, message);
    }

    public void log(Level level, String format, Object... args) {
        print(level, String.format(format, args));
    }
}
