package com.example.task01;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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

    private Logger(String name) {
        this.name = name;
    }

    public static Logger getLogger(String name) {
        Logger logger = INSTANCES.get(name);
        if (logger == null) {
            logger = new Logger(name);
            INSTANCES.put(name, logger);
        }
        return logger;
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

    public void debug(String message)                    { log(Level.DEBUG,   message); }
    public void debug(String template, Object... args)   { log(Level.DEBUG,   template, args); }
    public void info(String message)                     { log(Level.INFO,    message); }
    public void info(String template, Object... args)    { log(Level.INFO,    template, args); }
    public void warning(String message)                  { log(Level.WARNING, message); }
    public void warning(String template, Object... args) { log(Level.WARNING, template, args); }
    public void error(String message)                    { log(Level.ERROR,   message); }
    public void error(String template, Object... args)   { log(Level.ERROR,   template, args); }

    public void log(Level level, String message) {
        if (level.ordinal() < this.level.ordinal()) {
            return;
        }
        String date = LocalDate.now().format(DATE_FORMAT);
        String time = LocalTime.now().format(TIME_FORMAT);
        System.out.println("[" + level + "] " + date + " " + time + " " + name + " - " + message);
    }

    public void log(Level level, String template, Object... args) {
        log(level, String.format(template, args));
    }
}