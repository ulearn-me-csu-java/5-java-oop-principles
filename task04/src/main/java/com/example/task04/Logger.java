package com.example.task04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Logger {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    private final String name;
    private volatile LogLevel level;
    private final List<MessageHandler> handlers = new CopyOnWriteArrayList<>();

    public Logger(String name, LogLevel level, MessageHandler... handlers) {
        this.name = name;
        this.level = level;
        this.handlers.addAll(List.of(handlers));
    }

    public String getName() {
        return name;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public void addHandler(MessageHandler handler) {
        handlers.add(handler);
    }

    public void removeHandler(MessageHandler handler) {
        handlers.remove(handler);
    }

    public List<MessageHandler> getHandlers() {
        return new ArrayList<>(handlers);
    }

    public void log(LogLevel messageLevel, String message) {
        if (messageLevel.compareTo(level) < 0) {
            return;
        }
        String formatted = String.format("[%s] %s %s - %s",
                messageLevel, TIME_FORMAT.format(LocalDateTime.now()), name, message);
        for (MessageHandler handler : handlers) {
            handler.handle(formatted);
        }
    }

    public void log(LogLevel messageLevel, String format, Object... args) {
        if (messageLevel.compareTo(level) >= 0) {
            log(messageLevel, String.format(format, args));
        }
    }

    public void debug(String message) { log(LogLevel.DEBUG, message); }
    public void info(String message) { log(LogLevel.INFO, message); }
    public void warning(String message) { log(LogLevel.WARNING, message); }
    public void error(String message) { log(LogLevel.ERROR, message); }

    public void debug(String format, Object... args) { log(LogLevel.DEBUG, format, args); }
    public void info(String format, Object... args) { log(LogLevel.INFO, format, args); }
    public void warning(String format, Object... args) { log(LogLevel.WARNING, format, args); }
    public void error(String format, Object... args) { log(LogLevel.ERROR, format, args); }

    /** Закрыть все обработчики (сбросит накопленное в MemoryHandler). */
    public void close() {
        handlers.forEach(MessageHandler::close);
    }
}
