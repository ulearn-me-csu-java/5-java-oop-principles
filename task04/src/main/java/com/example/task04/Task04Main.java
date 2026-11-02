package com.example.task04;

import java.time.temporal.ChronoUnit;

public class Task04Main {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger("MyApplication");
        logger.setLevel(Logger.Level.DEBUG);

        // Вывод в консоль
        logger.addHandler(new ConsoleHandler());

        // Запись в обычный файл
        logger.addHandler(new FileHandler("application.log"));

        // Запись в файл с ротацией каждый час
        logger.addHandler(
                new RotationFileHandler("rotating", ChronoUnit.HOURS)
        );

        // Буфер в памяти: отправка в файл после 3 сообщений
        MemoryHandler memoryHandler =
                new MemoryHandler(new FileHandler("memory.log"), 3);

        logger.addHandler(memoryHandler);

        // Проверяем разные уровни сообщений
        logger.debug("Отладочное сообщение");

        logger.info("Приложение запущено");

        logger.warning("Предупреждение!");

        // Проверяем форматирование
        logger.error("Ошибка номер %d", 404);

        // Отправляем оставшиеся сообщения из памяти
        memoryHandler.flush();
    }
}