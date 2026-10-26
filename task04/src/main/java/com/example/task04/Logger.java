package com.example.task04;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logger {
    private final String name;
    private Level currentLevel = Level.DEBUG;
    private final List<MessageHandler> handlers = new ArrayList<>();
    
    private static final Map<String, Logger> loggers = new HashMap<>();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private Logger(String name) {
        this.name = name;
    }

    public static Logger getLogger(String name) {
        return loggers.computeIfAbsent(name, Logger::new);
    }

    // Добавление обработчика (консоль, файл и т.д.)
    public void addHandler(MessageHandler handler) {
        handlers.add(handler);
    }

    public void setLevel(Level level) {
        this.currentLevel = level;
    }

    public void log(Level level, String message) {
        if (level.ordinal() >= currentLevel.ordinal()) {
            String time = dateFormat.format(new Date());
            String formatted = String.format("[%s] %s %s - %s", level, time, name, message);
            
            // Передаем отформатированное сообщение всем обработчикам
            for (MessageHandler handler : handlers) {
                handler.handle(formatted);
            }
        }
    }

    // Обертки для удобства
    public void debug(String message) { log(Level.DEBUG, message); }
    public void info(String message) { log(Level.INFO, message); }
    public void warning(String message) { log(Level.WARNING, message); }
    public void error(String message) { log(Level.ERROR, message); }
}
