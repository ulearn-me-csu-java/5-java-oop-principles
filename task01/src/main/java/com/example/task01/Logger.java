package com.example.task01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Logger {

    //LocalDateTime now = LocalDateTime.now();
    private static final DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private static final DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
    //private final String formatData = now.format(formatterData);
    //private final String formatTime = now.format(formatterTime);

    private String name;
    private Level level = Level.INFO;
    private static Map<String, Logger> instances = new HashMap<>();

    private Logger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //----------------МЕТОД ГЕТТЕР-ЛОГГЕРА С ИСПОЛЬЗОВАНИЕМ ХЭШМАП---------------------
    public static Logger getLogger(String name) {
        Logger logger = instances.get(name);
        if (logger == null) {
            Logger logger1 = new Logger(name);
            instances.put(name, logger1);
            return logger1;
        } else {
            return logger;
        }
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public void log(Level level, String message) {
        if ((level.ordinal() < this.level.ordinal())) {
            return;
        }
        LocalDateTime dateTime = LocalDateTime.now();
        String formatData = dateTime.format(formatterData);
        String formatTime = dateTime.format(formatterTime);
        String result = String.format("[%s] %s %s %s - %s", level, formatData, formatTime, name, message);
        System.out.println(result);
    }

    public void log(Level level, String sample, Object... args) {
        log(level, String.format(sample, args));
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }

    public void error(String sample, Object... args) {
        log(Level.ERROR, sample, args);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void warning(String sample, Object... args) {
        log(Level.WARNING, sample, args);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void info(String sample, Object... args) {
        log(Level.INFO, sample, args);
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void debug(String sample, Object... args) {
        log(Level.DEBUG, sample, args);
    }

//    [<LEVEL>] <DATE> <TIME> <NAME> - <MESSAGE>


}
