package com.example.task04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logger
{
    private static final Map<String, Logger> LOGGERS = new HashMap<>();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    private final String name;
    private final List<MessageHandler> handlers = new ArrayList<>();
    private Level level = Level.DEBUG;

    private Logger(String name)
    {
        this.name = name;
    }

    public static Logger getLogger(String name)
    {
        Logger logger = LOGGERS.get(name);
        if (logger == null)
        {
            logger = new Logger(name);
            LOGGERS.put(name, logger);
        }
        return logger;
    }

    public String getName()
    {
        return name;
    }

    public Level getLevel()
    {
        return level;
    }

    public void setLevel(Level level)
    {
        this.level = level;
    }

    public void addHandler(MessageHandler handler)
    {
        handlers.add(handler);
    }

    public void removeHandler(MessageHandler handler)
    {
        handlers.remove(handler);
    }

    public List<MessageHandler> getHandlers()
    {
        return new ArrayList<>(handlers);
    }

    public void debug(String message)
    {
        log(Level.DEBUG, message);
    }

    public void debug(String template, Object... args)
    {
        log(Level.DEBUG, template, args);
    }

    public void info(String message)
    {
        log(Level.INFO, message);
    }

    public void info(String template, Object... args)
    {
        log(Level.INFO, template, args);
    }

    public void warning(String message)
    {
        log(Level.WARNING, message);
    }

    public void warning(String template, Object... args)
    {
        log(Level.WARNING, template, args);
    }

    public void error(String message)
    {
        log(Level.ERROR, message);
    }

    public void error(String template, Object... args)
    {
        log(Level.ERROR, template, args);
    }

    public void log(Level level, String message)
    {
        if (level.ordinal() < this.level.ordinal())
        {
            return;
        }

        String dateTime = LocalDateTime.now().format(FORMATTER);
        String formatted = String.format("[%s] %s %s - %s", level, dateTime, name, message);

        for (MessageHandler handler : handlers)
        {
            handler.handle(formatted);
        }
    }

    public void log(Level level, String template, Object... args)
    {
        log(level, String.format(template, args));
    }

    public enum Level
    {
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }
}