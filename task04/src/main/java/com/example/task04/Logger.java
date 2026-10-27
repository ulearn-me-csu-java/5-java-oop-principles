package com.example.task04;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Logger {
    private final String name;
    private Level currentLevel = Level.DEBUG;
    private static final HashMap<String, Logger> loggers = new HashMap<String, Logger>();
    private final ArrayList<MessageHandler> handlers = new ArrayList<MessageHandler>();
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    public String getName() {
        return name;
    }

    public Level getLevel() {
        return currentLevel;
    }

    public void setLevel(Level level) {
        currentLevel = level;
    }

    public void addHandler(MessageHandler handler) {
        for (MessageHandler h : handlers) {
            if (h.getClass().equals(handler.getClass()))
                throw new IllegalArgumentException(String.format("Обработчик %s уже существует!", handler.getClass().getSimpleName()));
        }

        handlers.add(handler);
    }

    public void addHandler(MessageHandler... handlers) {
        if (handlers.length == 0)
            throw new NullPointerException("Количество аргументов не должно равняться нулю!");

        for (MessageHandler handler : handlers) {
            addHandler(handler);
        }
    }

    public void removeHandler(MessageHandler handler) {
        if (handlers.isEmpty())
            throw new NullPointerException("Список обработчиков пуст!");

        boolean removed = handlers.removeIf(h -> h.getClass().equals(handler.getClass()));

        if (!removed)
            throw new IllegalArgumentException("Такого обработчика нет в списке!");
    }

    public void getHandlers() {
        if (handlers.isEmpty())
            System.out.println("Список обработчиков пуст");

        for (MessageHandler handler : handlers) {
            System.out.println(handler.getClass().getSimpleName());
        }
    }

    public Logger(String name) {
        this.name = name;
        loggers.put(name, this);
    }

    public static Logger getLogger(String name) {
        if (loggers.get(name) == null)
            new Logger(name);
        return loggers.get(name);
    }

    public void log(Level level, String message) {
        if (handlers.isEmpty())
            throw new NullPointerException("Список обработчиков пуст!");
        if (level.ordinal() < currentLevel.ordinal())
            return;

        Date date = new Date();
        String formattedMessage = String.format("[%s] %s %s - %s", level, formatter.format(date), name, message);

        for (MessageHandler handler : handlers) {
            handler.handle(formattedMessage);
        }
    }

    public void log(Level level, String pattern, Object... args) {
        if (args.length == 0)
            throw new IllegalArgumentException("Количество аргументов не может равняться нулю!");
        log(level, String.format(pattern, args));
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void debug(String pattern, Object... args) {
        log(Level.DEBUG, pattern, args);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void info(String pattern, Object... args) {
        log(Level.INFO, pattern, args);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void warning(String pattern, Object... args) {
        log(Level.WARNING, pattern, args);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }

    public void error(String pattern, Object... args) {
        log(Level.ERROR, pattern, args);
    }
}