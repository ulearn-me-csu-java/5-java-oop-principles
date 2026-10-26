package com.example.task01;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Logger {
    private final String name;
    private Level currentLevel;
    
    // Хранилище всех созданных логгеров (для паттерна Singleton)
    private static final Map<String, Logger> loggers = new HashMap<>();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private Logger(String name) {
        this.name = name;
        this.currentLevel = Level.DEBUG; // Уровень по умолчанию
    }

    // Получение экземпляра логгера
    public static Logger getLogger(String name) {
        if (!loggers.containsKey(name)) {
            loggers.put(name, new Logger(name));
        }
        return loggers.get(name);
    }

    public String getName() {
        return name;
    }

    public void setLevel(Level level) {
        this.currentLevel = level;
    }

    public Level getLevel() {
        return currentLevel;
    }

    // --- Основные методы логирования (с форматированием) ---
    public void debug(String message) {
        log(Level.DEBUG, message);
    }
    public void debug(String format, Object... args) {
        log(Level.DEBUG, String.format(format, args));
    }

    public void info(String message) {
        log(Level.INFO, message);
    }
    public void info(String format, Object... args) {
        log(Level.INFO, String.format(format, args));
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }
    public void warning(String format, Object... args) {
        log(Level.WARNING, String.format(format, args));
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }
    public void error(String format, Object... args) {
        log(Level.ERROR, String.format(format, args));
    }

    // --- Главный метод логирования ---
    public void log(Level level, String message) {
        // Печатаем только если уровень сообщения >= текущего установленного уровня
        if (level.ordinal() >= currentLevel.ordinal()) {
            String time = dateFormat.format(new Date());
            System.out.println(String.format("[%s] %s %s - %s", level, time, name, message));
        }
    }
}
