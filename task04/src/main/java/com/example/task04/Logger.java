package com.example.task04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logger {

    private static final Map<String, Logger> loggers = new HashMap<>();

    private final String name;

    public enum Level {
        DEBUG, INFO, WARNING, ERROR
    }

    private Level currentLevel = Level.DEBUG;

    private final List<MessageHandler> handlers = new ArrayList<>();

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    private Logger(String name) {
        this.name = name;
    }

    public static Logger getLogger(String name) {
        if (!loggers.containsKey(name)) {
            loggers.put(name, new Logger(name));
        }
        return loggers.get(name);
    }

    public String getName() {
        return name;
    }

    public void setLevel(Level level) {
        currentLevel = level;
    }

    public Level getLevel() {
        return currentLevel;
    }

    public void addHandler(MessageHandler handler) {
        handlers.add(handler);
    }

    private void printMessage(Level level, String message) {
        if (level.ordinal() >= currentLevel.ordinal()) {
            String dateTime = LocalDateTime.now().format(formatter);

            String formattedMessage =
                    "[" + level + "] " + dateTime + " "
                            + name + " - " + message;

            for (MessageHandler handler : handlers) {
                handler.handle(formattedMessage);
            }
        }
    }

    public void debug(String message) {
        printMessage(Level.DEBUG, message);
    }

    public void debug(String format, Object... args) {
        printMessage(Level.DEBUG, String.format(format, args));
    }

    public void info(String message) {
        printMessage(Level.INFO, message);
    }

    public void info(String format, Object... args) {
        printMessage(Level.INFO, String.format(format, args));
    }

    public void warning(String message) {
        printMessage(Level.WARNING, message);
    }

    public void warning(String format, Object... args) {
        printMessage(Level.WARNING, String.format(format, args));
    }

    public void error(String message) {
        printMessage(Level.ERROR, message);
    }

    public void error(String format, Object... args) {
        printMessage(Level.ERROR, String.format(format, args));
    }

    public void log(Level level, String message) {
        printMessage(level, message);
    }

    public void log(Level level, String format, Object... args) {
        printMessage(level, String.format(format, args));
    }
}