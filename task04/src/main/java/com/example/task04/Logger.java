package com.example.task04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logger {
    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static final Map<String, Logger> loggers = new HashMap<>();

    private final String name;
    private LogLevel level = LogLevel.DEBUG;
    private final List<MessageHandler> handlers = new ArrayList<>();

    private Logger(String name) {
        this.name = name;
    }

    public static Logger getLogger(String name) {
        if (!loggers.containsKey(name)) {
            loggers.put(name, new Logger(name));
        }
        return loggers.get(name);
    }

    public String getName() { return name; }

    public void setLevel(LogLevel level) { this.level = level; }
    public LogLevel getLevel() { return level; }

    public void addHandler(MessageHandler handler) {
        handlers.add(handler);
    }

    public void removeHandler(MessageHandler handler) {
        handlers.remove(handler);
    }

    public void debug(String message)   { log(LogLevel.DEBUG,   message); }
    public void info(String message)    { log(LogLevel.INFO,    message); }
    public void warning(String message) { log(LogLevel.WARNING, message); }
    public void error(String message)   { log(LogLevel.ERROR,   message); }

    public void debug(String t, Object... a)   { log(LogLevel.DEBUG,   t, a); }
    public void info(String t, Object... a)    { log(LogLevel.INFO,    t, a); }
    public void warning(String t, Object... a) { log(LogLevel.WARNING, t, a); }
    public void error(String t, Object... a)   { log(LogLevel.ERROR,   t, a); }

    public void log(LogLevel level, String message) {
        if (level.ordinal() < this.level.ordinal()) return;

        LocalDateTime now = LocalDateTime.now();
        String line = "[" + level + "] "
                + now.format(DATE) + " "
                + now.format(TIME) + " "
                + name + " - " + message;

        output(line);
    }

    public void log(LogLevel level, String template, Object... args) {
        log(level, String.format(template, args));
    }

    private void output(String message) {
        if (handlers.isEmpty()) {
            System.out.println(message);
            return;
        }
        for (MessageHandler handler : handlers) {
            handler.handle(message);
        }
    }
}
