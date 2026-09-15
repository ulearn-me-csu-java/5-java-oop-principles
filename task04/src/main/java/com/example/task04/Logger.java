package com.example.task04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Logger {
    public enum Level {
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }

    private static final Map<String, Logger> instances = new HashMap<>();
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final String name;
    private Level level = Level.DEBUG;
    private List<MessageHandler> handlers= new ArrayList<>();

    private Logger(String name) {
        this.name = name;
    }

    public static Logger getLogger(String name) {
        if (!instances.containsKey(name)) {
            instances.put(name, new Logger(name));
        }
        return instances.get(name);
    }

    public String getName() {
        return this.name;
    }

    public Level getLevel() {
        return this.level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void addHandler(MessageHandler handler){
        handlers.add(handler);
    }

    public void removeHandler(MessageHandler handler){
        handlers.remove(handler);
    }

    public void log(Level level, String message) {
        if (level.ordinal() < this.level.ordinal()) {
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        String full = "[" + level + "] " + now.format(dateFormat) + " " + now.format(timeFormat)
                + " " + this.name + " - " + message;

        for (MessageHandler  handler: handlers){
            handler.handle(full);
        }
    }

    public void log(Level level, String template, Object... args) {
        log(level, String.format(template, args));
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void debug(String template, Object... args) {
        log(Level.DEBUG, template, args);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void info(String template, Object... args) {
        log(Level.INFO, template, args);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void warning(String template, Object... args) {
        log(Level.WARNING, template, args);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }

    public void error(String template, Object... args) {
        log(Level.ERROR, template, args);
    }
}
