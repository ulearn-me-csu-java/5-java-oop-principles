package com.example.task04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logger {

    private static final Map<String, Logger> instances = new HashMap<>();
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    private final String name;
    private Level level = Level.DEBUG;
    private final List<MessageHandler> handlers = new ArrayList<>();

    private Logger(String name) {
        this.name = name;
    }

    public static Logger getLogger(String name) {
        return instances.computeIfAbsent(name, Logger::new);
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

    public void log(Level messageLevel, String message) {
        if (messageLevel.ordinal() < level.ordinal()) {
            return;
        }
        String text = String.format("[%s] %s %s - %s",
                messageLevel, LocalDateTime.now().format(FORMAT), name, message);
        for (MessageHandler handler : handlers) {
            handler.handle(text);
        }
    }

    public void log(Level messageLevel, String template, Object... args) {
        log(messageLevel, String.format(template, args));
    }

    public void debug(String message) { log(Level.DEBUG, message); }
    public void info(String message) { log(Level.INFO, message); }
    public void warning(String message) { log(Level.WARNING, message); }
    public void error(String message) { log(Level.ERROR, message); }

    public void debug(String template, Object... args) { log(Level.DEBUG, template, args); }
    public void info(String template, Object... args) { log(Level.INFO, template, args); }
    public void warning(String template, Object... args) { log(Level.WARNING, template, args); }
    public void error(String template, Object... args) { log(Level.ERROR, template, args); }
}
