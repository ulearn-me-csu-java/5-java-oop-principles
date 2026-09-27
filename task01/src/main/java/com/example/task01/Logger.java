package com.example.task01;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private final String name;
    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static final Map<String, Logger> loggers = new HashMap<>();
    private LogLevel level = LogLevel.DEBUG; //все сообщения по умолчанию

    private Logger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Logger getLogger(String name) {
        Logger logger = loggers.get(name);
        if (logger == null) {
            logger = new Logger(name);
            loggers.put(name, logger);
        }
        return logger;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void debug(String template, Object... args) {
        log(LogLevel.DEBUG, template, args);
    }

    public void info(String template, Object... args) {
        log(LogLevel.INFO, template, args);
    }

    public void warning(String template, Object... args) {
        log(LogLevel.WARNING, template, args);
    }

    public void error(String template, Object... args) {
        log(LogLevel.ERROR, template, args);
    }

    public void log(LogLevel level, String message) {
        if (level.ordinal() < this.level.ordinal()) {
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        String line = "[" + level + "]" + " "
                + now.format(DATE) + " "
                + now.format(TIME) + " "
                + name + " - " + message;
        System.out.println(line);
    }

    public void log(LogLevel level, String template, Object... args) {
        log(level, String.format(template, args));
    }
}
