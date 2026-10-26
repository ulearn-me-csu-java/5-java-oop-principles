package com.example.task04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Logger {
    private static final Map<String, Logger> REGISTRY = new ConcurrentHashMap<>();
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final String name;
    private volatile Level level;

    // список обработчиков
    private final List<MessageHandler> handlers = new ArrayList<>();

    private Logger(String name, Level initialLevel) {
        this.name = name;
        this.level = initialLevel;
    }

    public static Logger getLogger(String name) {
        if (name == null) throw new IllegalArgumentException("Logger name must not be null");
        return REGISTRY.computeIfAbsent(name, n -> new Logger(n, Level.DEBUG));
    }

    // метод для добавления обработчика
    public void addHandler(MessageHandler handler) {
        handlers.add(handler);
    }

    public String getName() { return name; }
    public Level getLevel() { return level; }
    public void setLevel(Level level) { this.level = level; }

    public void debug(String msg) { log(Level.DEBUG, msg); }
    public void info(String msg) { log(Level.INFO, msg); }
    public void warning(String msg) { log(Level.WARNING, msg); }
    public void error(String msg) { log(Level.ERROR, msg); }

    public void log(Level level, String message) {
        if (level.ordinal() < this.level.ordinal()) return;

        LocalDateTime now = LocalDateTime.now();
        String line = String.format("[%s] %s %s %s - %s",
                level.name(), now.format(DATE_FMT), now.format(TIME_FMT), this.name, message);

        // разослать всем обработчикам
        for (MessageHandler handler : handlers) {
            handler.handle(line);
        }
    }
}