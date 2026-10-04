package com.example.task04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Logger {

    private static final Map<String, Logger> loggers = new HashMap<>();

    private final String name;

    private Level level = Level.DEBUG;

    private final MessageHandler[] handlers =
            new MessageHandler[100];

    private int handlerCount = 0;

    public enum Level {
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }

    private Logger(String name) {
        this.name = name;
    }

    public static Logger getLogger(String name) {

        if (loggers.containsKey(name)) {
            return loggers.get(name);
        }

        Logger logger = new Logger(name);
        loggers.put(name, logger);

        return logger;
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

    public void addHandler(MessageHandler handler) {

        if (handlerCount >= handlers.length) {
            throw new IllegalStateException(
                    "Слишком много обработчиков");
        }

        handlers[handlerCount] = handler;
        handlerCount++;
    }

    public void removeHandler(MessageHandler handler) {

        for (int i = 0; i < handlerCount; i++) {

            if (handlers[i] == handler) {

                for (int j = i; j < handlerCount - 1; j++) {
                    handlers[j] = handlers[j + 1];
                }

                handlers[handlerCount - 1] = null;
                handlerCount--;

                return;
            }
        }
    }

    public void log(Level level, String message) {

        if (level.ordinal() < this.level.ordinal()) {
            return;
        }

        String formattedMessage = formatMessage(level, message);

        for (int i = 0; i < handlerCount; i++) {
            handlers[i].handle(formattedMessage);
        }
    }

    public void log(Level level, String message, Object... args) {
        log(level, String.format(message, args));
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void debug(String message, Object... args) {
        log(Level.DEBUG, message, args);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void info(String message, Object... args) {
        log(Level.INFO, message, args);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void warning(String message, Object... args) {
        log(Level.WARNING, message, args);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }

    public void error(String message, Object... args) {
        log(Level.ERROR, message, args);
    }

    private String formatMessage(Level level, String message) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

        String dateTime = LocalDateTime.now().format(formatter);

        return "[" + level + "] "
                + dateTime
                + " "
                + name
                + " - "
                + message;
    }
}