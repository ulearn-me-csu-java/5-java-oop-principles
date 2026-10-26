package com.example.task01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Logger {

    // синглтон
    // хранит пары: "имя логгера" -> "сам объект логгера"
    private static final Map<String, Logger> REGISTRY = new ConcurrentHashMap<>();

    // настройки формата даты и времени
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm:ss");

    // переменные экземпляра
    private final String name;       // Имя (не меняется)
    private volatile Level level;    // Текущий уровень (меняется через setLevel)

    // приватный конструктор
    // Чтобы никто не мог написать new Logger("...") и создать дубликат
    private Logger(String name, Level initialLevel) {
        this.name = name;
        this.level = initialLevel;
    }

    // точка входа
    public static Logger getLogger(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Logger name must not be null");
        }
        // если логер с именем есть вернет его, если нет создаст новый
        return REGISTRY.computeIfAbsent(name, n -> new Logger(n, Level.DEBUG));
    }

    public String getName() {
        return name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        if (level == null) {
            throw new IllegalArgumentException("Level must not be null");
        }
        this.level = level;
    }

    // методы уровней/делегирование
    // передадут главному методу лог

    public void debug(String message) { log(Level.DEBUG, message); }
    public void debug(String template, Object... args) { log(Level.DEBUG, template, args); }

    public void info(String message) { log(Level.INFO, message); }
    public void info(String template, Object... args) { log(Level.INFO, template, args); }

    public void warning(String message) { log(Level.WARNING, message); }
    public void warning(String template, Object... args) { log(Level.WARNING, template, args); }

    public void error(String message) { log(Level.ERROR, message); }
    public void error(String template, Object... args) { log(Level.ERROR, template, args); }

    // главный движок, фильтрация и печать
    public void log(Level level, String message) {
        // фильтр если сообщ меньше установленного стоп
        if (level.ordinal() < this.level.ordinal()) {
            return; // выводим не печатая
        }

        // собираем строку по шаблону тз
        LocalDateTime now = LocalDateTime.now();
        String line = String.format("[%s] %s %s %s - %s",
                level.name(),
                now.format(DATE_FMT),
                now.format(TIME_FMT),
                this.name,
                message);

        // печатаем
        System.out.println(line);
    }

    // вторая перегрузка log для шаблонов
    public void log(Level level, String template, Object... args) {
        // сначала форматируем строку, потом отдаем первому методу log
        log(level, String.format(template, args));
    }
}