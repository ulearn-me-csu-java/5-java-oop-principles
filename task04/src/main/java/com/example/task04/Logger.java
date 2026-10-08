package com.example.task04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static ConcurrentHashMap<String, Logger> LoggerMap = new ConcurrentHashMap<>();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    private String name;
    private List<MessageHandler> handlers = new ArrayList<>();

    public String getName() {
        return name;
    }

    public static Logger getLogger(String name) {
        LoggerMap.computeIfAbsent(name, k -> new Logger(k));
        return LoggerMap.get(name);
    }

    public enum LogLevel {
        DEBUG, INFO, WARNING, ERROR
    }

    private LogLevel level = LogLevel.DEBUG;

    public void setLevel(LogLevel l) {
        level = l;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void addHandler(MessageHandler handler) {
        handlers.add(handler);
    }

    public Logger(String name) {
        this.name = name;
    }

    public Logger(String name, MessageHandler... handlers) {
        this.name = name;
        for (MessageHandler h : handlers) {
            this.handlers.add(h);
        }
    }

    private void notifyHandlers(String message) {
        if (handlers.isEmpty()) {
            System.out.println(message);
        } else {
            for (MessageHandler handler : handlers) {
                handler.log(message);
            }
        }
    }

    public void error(String massage) {
        if (level != null && LogLevel.ERROR.ordinal() < level.ordinal()) {
            return;
        }
        String date = LocalDateTime.now().format(formatter);
        notifyHandlers("[ERROR] " + date + " " + name + " - " + massage);
    }

    public void error(String tempalate, Object... args) {
        if (level != null && LogLevel.ERROR.ordinal() < level.ordinal()) {
            return;
        }
        String date = LocalDateTime.now().format(formatter);
        notifyHandlers("[ERROR] " + date + " " + name + " - " + String.format(tempalate, args));
    }

    public void warning(String massage) {
        if (level != null && LogLevel.WARNING.ordinal() < level.ordinal()) {
            return;
        }
        String date = LocalDateTime.now().format(formatter);
        notifyHandlers("[WARNING] " + date + " " + name + " - " + massage);
    }

    public void warning(String tempalate, Object... args) {
        if (level != null && LogLevel.WARNING.ordinal() < level.ordinal()) {
            return;
        }
        String date = LocalDateTime.now().format(formatter);
        notifyHandlers("[WARNING] " + date + " " + name + " - " + String.format(tempalate, args));
    }

    public void info(String massage) {
        if (level != null && LogLevel.INFO.ordinal() < level.ordinal()) {
            return;
        }
        String date = LocalDateTime.now().format(formatter);
        notifyHandlers("[INFO] " + date + " " + name + " - " + massage);
    }

    public void info(String tempalate, Object... args) {
        if (level != null && LogLevel.INFO.ordinal() < level.ordinal()) {
            return;
        }
        String date = LocalDateTime.now().format(formatter);
        notifyHandlers("[INFO] " + date + " " + name + " - " + String.format(tempalate, args));
    }

    public void debug(String massage) {
        if (level != null && LogLevel.DEBUG.ordinal() < level.ordinal()) {
            return;
        }
        String date = LocalDateTime.now().format(formatter);
        notifyHandlers("[DEBUG] " + date + " " + name + " - " + massage);
    }

    public void debug(String tempalate, Object... args) {
        if (level != null && LogLevel.DEBUG.ordinal() < level.ordinal()) {
            return;
        }
        String date = LocalDateTime.now().format(formatter);
        notifyHandlers("[DEBUG] " + date + " " + name + " - " + String.format(tempalate, args));
    }

    public void log(LogLevel level, String massage) {
        if (this.level != null && level.ordinal() < this.level.ordinal()) {
            return;
        }
        String date = LocalDateTime.now().format(formatter);
        notifyHandlers("[" + level + "] " + date + " " + name + " - " + massage);
    }

    public void log(LogLevel level, String tempalte, Object... args) {
        if (this.level != null && level.ordinal() < this.level.ordinal()) {
            return;
        }
        String date = LocalDateTime.now().format(formatter);
        notifyHandlers("[" + level + "] " + date + " " + name + " - " + String.format(tempalte, args));
    }
}
