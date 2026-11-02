
package com.example.task01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Logger {

    // Уровни важности сообщений
    public enum Level {
        DEBUG, INFO, WARNING, ERROR
    }

    // Хранилище всех созданных логгеров
    private static final Map<String, Logger> loggers = new HashMap<>();

    // Имя логгера
    private final String name;

    // Текущий уровень важности
    private Level level = Level.DEBUG;

    // Конструктор
    private Logger(String name) {
        this.name = name;
    }

    // Получение логгера по имени
    public static Logger getLogger(String name) {
        if (!loggers.containsKey(name)) {
            loggers.put(name, new Logger(name));
        }

        return loggers.get(name);
    }

    // Получение имени
    public String getName() {
        return name;
    }

    // Установка уровня
    public void setLevel(Level level) {
        this.level = level;
    }

    // Получение уровня
    public Level getLevel() {
        return level;
    }

    // Вывод сообщения
    private void printMessage(Level messageLevel, String message) {
        if (messageLevel.ordinal() < level.ordinal()) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();

        String date = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        System.out.println(
                "[" + messageLevel + "] " +
                        date + " " +
                        time + " " +
                        name + " - " +
                        message
        );
    }

    // Форматирование сообщения
    private String format(String pattern, Object... args) {
        return String.format(pattern, args);
    }

    // DEBUG
    public void debug(String message) {
        printMessage(Level.DEBUG, message);
    }

    public void debug(String pattern, Object... args) {
        debug(format(pattern, args));
    }

    // INFO
    public void info(String message) {
        printMessage(Level.INFO, message);
    }

    public void info(String pattern, Object... args) {
        info(format(pattern, args));
    }

    // WARNING
    public void warning(String message) {
        printMessage(Level.WARNING, message);
    }

    public void warning(String pattern, Object... args) {
        warning(format(pattern, args));
    }

    // ERROR
    public void error(String message) {
        printMessage(Level.ERROR, message);
    }

    public void error(String pattern, Object... args) {
        error(format(pattern, args));
    }

    // Универсальный метод log
    public void log(Level level, String message) {
        printMessage(level, message);
    }

    public void log(Level level, String pattern, Object... args) {
        log(level, format(pattern, args));
    }
}
