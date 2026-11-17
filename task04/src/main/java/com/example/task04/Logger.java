package com.example.task04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Logger {
    public enum Level { DEBUG, INFO, WARNING, ERROR }

    private static final Map<String, Logger> CACHE = new HashMap<>();
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    private final String name;
    private Level level;
    
    private final List<MessageHandler> handlers = new CopyOnWriteArrayList<>();

    private Logger(String name) {
        this(name, Level.DEBUG);
    }

    private Logger(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public static Logger getLogger(String name) {
        return CACHE.computeIfAbsent(name, Logger::new);
    }

    public String getName() { return name; }
    public void setLevel(Level level) { this.level = level; }
    public Level getLevel() { return level; }

    public void addHandler(MessageHandler handler) {
        handlers.add(handler);
    }

    public void removeHandler(MessageHandler handler) {
        handlers.remove(handler);
    }

    public void log(Level level, String template, Object... args) {
        if (level.ordinal() < this.level.ordinal()) return;

        String msg = args.length == 0 ? template : String.format(template, args);
        String formattedMessage = String.format("[%s] %s %s - %s",
                level, LocalDateTime.now().format(FMT), name, msg);

        for (MessageHandler handler : handlers) {
            handler.handle(formattedMessage);
        }
    }

    public void debug(String msg, Object... args)   { log(Level.DEBUG, msg, args); }
    public void info(String msg, Object... args)    { log(Level.INFO, msg, args); }
    public void warning(String msg, Object... args) { log(Level.WARNING, msg, args); }
    public void error(String msg, Object... args)   { log(Level.ERROR, msg, args); }
}