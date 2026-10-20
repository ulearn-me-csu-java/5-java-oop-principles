package com.example.task01;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;

public class Logger {
    private static final Map<String, Logger> LOGGERS = new HashMap<>();
    private final String name;
    private Level level = Level.DEBUG;

    private Logger(String name){
        this.name = name;
    }

    public static Logger getLogger(String name){
        return LOGGERS.computeIfAbsent(name, Logger::new);
    }

    public void setLevel(Level level){
        this.level = level;
    }
    public Level getLevel(){
        return level;
    }

    public void log(Level level, String message){
        if (level.ordinal() < this.level.ordinal()){
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.now();
        String formatData = dateTime.format(formatter);
        String result = String.format("[%s] %s %s - %s", level, formatData, name, message);
        System.out.println(result);
    }

    public void log(Level level, String template, Object... args){
        log(level, String.format(template, args));
    }

    public void debug(String message){
        log(Level.DEBUG, message);
    }

    public void debug(String template, Object... args){
        log(Level.DEBUG, template, args);
    }

    public void info(String message){
        log(Level.INFO, message);
    }

    public void info(String template, Object... args){
        log(Level.INFO, template, args);
    }

    public void warning(String message){
        log(Level.WARNING, message);
    }

    public void warning(String template, Object... args){
        log(Level.WARNING, template, args);
    }

    public void error(String message){
        log(Level.ERROR, message);
    }

    public void error(String template, Object... args){
        log(Level.ERROR, template, args);
    }

    public String getName(){
        return this.name;
    }
}
