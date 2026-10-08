package com.example.task01;
import java.util.concurrent.ConcurrentHashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static ConcurrentHashMap<String,Logger> LoggerMap = new ConcurrentHashMap<>();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    private String name;
    public String getName() {
        return name;
    }
    public static Logger getLogger(String name)
    {
        LoggerMap.computeIfAbsent(name,k -> new Logger(k));
        return LoggerMap.get(name);
    }
    public enum LogLevel {
        DEBUG, INFO, WARNING, ERROR
    }
    private LogLevel level = LogLevel.DEBUG;
    public void setLevel(LogLevel l)
    {
        level = l;
    }
    public LogLevel getLevel()
    {
        return level;
    }
    private Logger(String name)
    {
        this.name = name;
    }
    public void error(String massage)
    {
        if (level != null && LogLevel.ERROR.ordinal() < level.ordinal()) {
            return;
        }
        String date = LocalDateTime.now().format(formatter);
        System.out.println("[ERROR] " + date + " " + name + " - " + massage);
    }
    public void error(String tempalate, Object... args)
    {
        if (level != null && LogLevel.ERROR.ordinal() < level.ordinal()) {
            return;
        }
        String date = LocalDateTime.now().format(formatter);
        System.out.println("[ERROR] " + date + " " + name + " - " + String.format(tempalate, args));
    }
    public void warning(String massage)
    {
        if (level != null && LogLevel.WARNING.ordinal() < level.ordinal()) {
            return;
        }
        String date = LocalDateTime.now().format(formatter);
        System.out.println("[WARNING] " + date + " " + name + " - " + massage);
    }
    public void warning(String tempalate, Object... args)
    {
        if (level != null && LogLevel.WARNING.ordinal() < level.ordinal()) {
            return;
        }
        String date = LocalDateTime.now().format(formatter);
        System.out.println("[WARNING] " + date + " " + name + " - " + String.format(tempalate, args));
    }
    public void info(String massage)
    {
        if (level != null && LogLevel.INFO.ordinal() < level.ordinal()) {
            return;
        }
        String date = LocalDateTime.now().format(formatter);
        System.out.println("[INFO] " + date + " " + name + " - " + massage);
    }
    public void info(String tempalate, Object... args)
    {
        if (level != null && LogLevel.INFO.ordinal() < level.ordinal()) {
            return;
        }
        String date = LocalDateTime.now().format(formatter);
        System.out.println("[INFO] " + date + " " + name + " - " + String.format(tempalate, args));
    }
    public void debug(String massage)
    {
        if (level != null && LogLevel.DEBUG.ordinal() < level.ordinal()) {
            return;
        }
        String date = LocalDateTime.now().format(formatter);
        System.out.println("[DEBUG] " + date + " " + name + " - " + massage);
    }
    public void debug(String tempalate, Object... args)
    {
        if (level != null && LogLevel.DEBUG.ordinal() < level.ordinal()) {
            return;
        }
        String date = LocalDateTime.now().format(formatter);
        System.out.println("[DEBUG] " + date + " " + name + " - " + String.format(tempalate, args));
    }
    public void log(LogLevel level, String massage)
    {
        if (this.level != null && level.ordinal() < this.level.ordinal()) {
            return;
        }
        String date = LocalDateTime.now().format(formatter);
        System.out.println("[" + level + "] " + date + " " + name + " - " + massage);
    }
    public void log(LogLevel level, String tempalte, Object... args)
    {
        if (this.level != null && level.ordinal() < this.level.ordinal()) {
            return;
        }
        String date = LocalDateTime.now().format(formatter);
        System.out.println("[" + level + "] " + date + " " + name + " - " + String.format(tempalte, args));
    }

}
