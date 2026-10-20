package com.example.task04;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logger {

    public enum Level {
        DEBUG, INFO, WARNING, ERROR
    }

    private static final Map<String, Logger> INSTANCES = new HashMap<>();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final String name;
    private Level level = Level.DEBUG;
    private final List<MessageHandler> handlers = new ArrayList<>();

    private Logger(String name) {
        this.name = name;
    }

    public static Logger getLogger(String name) {
        return INSTANCES.computeIfAbsent(name, Logger::new);
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

    public void removeHandler(MessageHandler handler) {
        handlers.remove(handler);
    }

    // ... методы уровней (debug, info, warning, error) остаются такими же, как в задании 1 ...
    public void debug(String message) { log(Level.DEBUG, message); }
    public void debug(String template, Object... args) { log(Level.DEBUG, template, args); }
    public void info(String message) { log(Level.INFO, message); }
    public void info(String template, Object... args) { log(Level.INFO, template, args); }
    public void warning(String message) { log(Level.WARNING, message); }
    public void warning(String template, Object... args) { log(Level.WARNING, template, args); }
    public void error(String message) { log(Level.ERROR, message); }
    public void error(String template, Object... args) { log(Level.ERROR, template, args); }

    public void log(Level level, String message) {
        if (level.ordinal() < this.level.ordinal()) {
            return;
        }
        String date = LocalDate.now().format(DATE_FORMAT);
        String time = LocalTime.now().format(TIME_FORMAT);
        String formattedMessage = "[" + level + "] " + date + " " + time + " " + name + " - " + message;

        // Рассылаем сообщение всем обработчикам
        for (MessageHandler handler : handlers) {
            handler.handle(formattedMessage);
        }
    }

    public void log(Level level, String template, Object... args) {
        log(level, String.format(template, args));
    }
}