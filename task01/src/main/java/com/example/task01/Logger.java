package com.example.task01;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Logger {
    public enum Level {
        DEBUG, INFO, WARNING, ERROR
    }

    private String name;
    private Level level;

    private Logger(String name){
        this.name=name;
        this.level=level.DEBUG;
    }

    private static final Map<String, Logger> loggers = new ConcurrentHashMap<>();

    public static Logger getLogger(String name) {
        return loggers.computeIfAbsent(name,Logger::new);
    }

    public String getName(){
        return this.name;
    }
    public Level getLevel() { return level; }

    public void setLevel(Level level) {
        this.level = level;
    }


    // DEBUG
    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void debug(String format, Object... args) {
        log(Level.DEBUG, format, args);
    }

    // INFO
    public void info(String message) {
        log(Level.INFO, message);
    }

    public void info(String format, Object... args) {
        log(Level.INFO, format, args);
    }

    // WARNING
    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void warning(String format, Object... args) {
        log(Level.WARNING, format, args);
    }

    // ERROR
    public void error(String message) {
        log(Level.ERROR, message);
    }

    public void error(String format, Object... args) {
        log(Level.ERROR, format, args);
    }

    public void log(Level level, String message) {
        if (level.ordinal() >= this.level.ordinal()) {
            print(level, message);
        }
    }

    public void log(Level level, String format, Object... args) {
        if (level.ordinal() >= this.level.ordinal()) {
            String message;
            try {
                if (format.contains("{0}")) {
                    message = MessageFormat.format(format, args);
                } else {
                    message = String.format(format, args);
                }
            } catch (Exception e) {
                message = format;
            }
            print(level, message);
        }
    }

    private void print(Level level, String message) {
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        System.out.printf("[%s] %s %s %s - %s%n", level.name(), date, time, name, message);
    }
}
