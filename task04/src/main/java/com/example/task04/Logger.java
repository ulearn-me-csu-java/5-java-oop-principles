package com.example.task04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Logger{
    public enum LogLevel {
        DEBUG, INFO, WARNING, ERROR
    }
    private final String name;

    private LogLevel level;

    private static final Map<String, Logger> loggers = new HashMap<>();

    private final List<MessageHandler> handlers = new ArrayList<>();

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private Logger (String name){
        this.name = name;
        this.level = LogLevel.DEBUG;
    }

    public String getName() {
        return name;
    }
    public static Logger getLogger(String name) {
        if (!loggers.containsKey(name)) {
            loggers.put(name, new Logger(name));
        }
        return loggers.get(name);
    }

    public void addHandler(MessageHandler handler) {
        handlers.add(handler);
    }

    public void removeHandler(MessageHandler handler) {
        handlers.remove(handler);
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public void log(LogLevel msgLevel, String message) {
        if (msgLevel.ordinal() < this.level.ordinal()) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DATE_FORMATTER);
        String time = now.format(TIME_FORMATTER);

        // Формируем финальную строку: [<LEVEL>] <DATE> <TIME> <NAME> - <MESSAGE>
        String finalMessage = String.format("[%s] %s %s %s - %s", msgLevel.name(), date, time, this.name, message);
        for (MessageHandler handler : handlers) {
            handler.handler(finalMessage);
        }
    }

    public void log(LogLevel msgLevel, String format, Object... args) {
        log(msgLevel, String.format(format, args));
    }

    // DEBUG
    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void debug(String format, Object... args) {
        log(LogLevel.DEBUG, format, args);
    }

    // INFO
    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void info(String format, Object... args) {
        log(LogLevel.INFO, format, args);
    }

    // WARNING
    public void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    public void warning(String format, Object... args) {
        log(LogLevel.WARNING, format, args);
    }

    // ERROR
    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void error(String format, Object... args) {
        log(LogLevel.ERROR, format, args);
    }
}

