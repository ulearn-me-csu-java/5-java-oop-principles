package com.example.task04;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logger {
    private static final Map<String, Logger> loggers = new HashMap<>();

    private final String name;
    private Level level = Level.DEBUG;
    private final List<MessageHandler> handlers = new ArrayList<>();

    public Logger(String name, MessageHandler... handlers) {
        this.name = name;
        if (handlers != null && handlers.length > 0) {
            this.handlers.addAll(Arrays.asList(handlers));
        } else {
            this.handlers.add(new ConsoleHandler());
        }
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void addHandler(MessageHandler handler) {
        handlers.add(handler);
    }

    public void log(Level level, String message) {
        if (level.ordinal() >= this.level.ordinal()) {
            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            String formattedMessage = String.format("[%s] %s %s %s - %s", level, date, time, name, message);
            for (MessageHandler handler : handlers) {
                handler.handle(formattedMessage);
            }
        }
    }

    public void log(Level level, String format, Object... args) {
        if (format.contains("{0}") || format.contains("{1}")) {
            log(level, MessageFormat.format(format, args));
        } else {
            log(level, String.format(format, args));
        }
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void debug(String format, Object... args) {
        log(Level.DEBUG, format, args);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void info(String format, Object... args) {
        log(Level.INFO, format, args);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void warning(String format, Object... args) {
        log(Level.WARNING, format, args);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }

    public void error(String format, Object... args) {
        log(Level.ERROR, format, args);
    }
}
