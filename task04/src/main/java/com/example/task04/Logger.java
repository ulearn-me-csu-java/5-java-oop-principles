package com.example.task04;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Logger {
    private final String name;
    private Level currentLevel = Level.DEBUG;

    static Logger[] loggers = new Logger[0];

    static List<MessageHandler> handlers = new CopyOnWriteArrayList<>();

    public enum Level{
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }


    private Logger (String name){
        this.name = name;
        this.handlers.add(new ConsoleHandler());
    }

    public String getName(){
        return name;
    }

    public static Logger getLogger(String name){

        int length = loggers.length;

        if (length == 0){
            Logger log = new Logger(name);
            loggers = new Logger[] {log};
            return log;

        } else {
            for (int i = 0; i < loggers.length; i++) {
                if (loggers[i].getName() == name){
                    return loggers[i];
                }
            }

            Logger[] loggers1 = new Logger[length + 1];
            for (int j = 0; j < length; j++) {
                loggers1[j] = loggers[j];
            }
            Logger log = new Logger(name);
            loggers1[length] = log;
            loggers = loggers1;

            return log;
        }
    }

    public void setLevel(Level level) {
        this.currentLevel = level;
    }

    public Level getLevel(){
        return currentLevel;
    }

    // ---- Логирование ----

    public void log(Level level, String message) {
        if(level.ordinal() < currentLevel.ordinal()){
            return;
        }
        String formatted = formatMessage(level, message);
        for (MessageHandler handler : handlers){
            handler.handle(level, formatted);
        }
    }

    public void log(Level level, String template, Object... args){
        log(level, String.format(template,  args));
    }

    private String formatMessage(Level level, String message){
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        return String.format("[%s] %s %s %s - %s", level, date, dateTime, name, message);
    }

    // ---- Методы по уровням ----

    public void debug(String massage) {
        log(Level.DEBUG, massage);
    }

    public void debug(String template, Object... args) {
        log(Level.DEBUG, template, args);
    }

    public void info(String massage) {
        log(Level.INFO, massage);
    }

    public void info(String template, Object... args) {
        log(Level.INFO, template, args);
    }

    public void warning(String massage) {
        log(Level.WARNING, massage);
    }

    public void warning(String template, Object... args) {
        log(Level.WARNING, template, args);
    }

    public void error(String massage) {
        log(Level.ERROR, massage);
    }

    public void error(String template, Object... args) {
        log(Level.ERROR, template, args);
    }

    // ---- Обработчики ----

    public void addHandler(MessageHandler handler){
        handlers.add(handler);
    }

    public void removeHandler(MessageHandler handler){
        handlers.remove(handler);
    }

    public void clearHandler(){
        handlers.clear();
    }

    public List<MessageHandler> getHandlers(){
        return List.copyOf(handlers);
    }
}
