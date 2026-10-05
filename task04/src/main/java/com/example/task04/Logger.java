package com.example.task04;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logger {
    private final String name;
    private Level level = Level.DEBUG;

    private final List<MessageHandler> handlers = new ArrayList<>();
    private static final Map<String, Logger> loggers = new HashMap<>();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private Logger(String name) {
        this.name = name;
    }

    public static Logger getLogger(String name) {
        Logger logger = loggers.get(name);
        if (logger == null) {
            logger = new Logger(name);
            loggers.put(name, logger);
        }
        return logger;
    }

    public void addHandler(MessageHandler handler){
        handlers.add(handler);
    }

    public void removeHandler(MessageHandler handler){
        handlers.remove(handler);
    }

    public String getName() {
        return name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    private String logMessage(Level msgLevel, String message) {
        String date = LocalDate.now().format(DATE_FORMAT);
        String time = LocalTime.now().format(TIME_FORMAT);

        return String.format("[%s] %s %s %s - %s", msgLevel, date, time, this.name, message);
    }


    private void print(Level msgLevel, String message) {
        if (msgLevel.ordinal() < level.ordinal()) {
            return;
        }
        String logMessages = logMessage(msgLevel, message);

        for (MessageHandler handler : handlers) {
            handler.handler(logMessages); }
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