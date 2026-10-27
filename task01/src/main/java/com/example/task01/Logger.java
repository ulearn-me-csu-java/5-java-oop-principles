package com.example.task01;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Logger {
    private static HashMap<String, Logger> loggers = new HashMap<String, Logger>();
    private final String name;
    private Level currentLevel = Level.DEBUG;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    public String getName() {
        return name;
    }

    public Level getLevel() {
        return currentLevel;
    }

    public void setLevel(Level level) {
        currentLevel = level;
    }

    public Logger(String name) {
        this.name = name;
        loggers.put(name, this);
    }

    public static Logger getLogger(String name) {
        if (loggers.get(name) == null)
            new Logger(name);
        return loggers.get(name);
    }

    public void log(Level level, String message) {
        if (level.ordinal() < currentLevel.ordinal())
            return;
        Date date = new Date();
        String formattedMessage = String.format("[%s] %s %s - %s", level, formatter.format(date), name, message);
        System.out.println(formattedMessage);
    }

    public void log(Level level, String pattern, Object... args) {
        if (args.length == 0)
            throw new IllegalArgumentException("Количество аргументов не может равняться нулю!");
        log(level, String.format(pattern, args));
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void debug(String pattern, Object... args) {
        log(Level.DEBUG, pattern, args);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void info(String pattern, Object... args) {
        log(Level.INFO, pattern, args);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void warning(String pattern, Object... args) {
        log(Level.WARNING, pattern, args);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }

    public void error(String pattern, Object... args) {
        log(Level.ERROR, pattern, args);
    }
}