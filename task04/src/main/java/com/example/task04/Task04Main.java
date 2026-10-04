package com.example.task04;

import java.time.temporal.ChronoUnit;

public class Task04Main {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger("myLogger");

        // 1. Вывод в консоль
        ConsoleHandler consoleHandler = new ConsoleHandler();

        logger.addHandler(consoleHandler);

        logger.debug("Отладочное сообщение");
        logger.info("Информационное сообщение");
        logger.warning("Предупреждение");
        logger.error("Ошибка");


        // 2. Вывод в файл
        FileHandler fileHandler =
                new FileHandler("log.txt");

        logger.addHandler(fileHandler);

        logger.info("Это сообщение попадет и в консоль, и в файл");


        // 3. Ротация файлов
        RotationFileHandler rotationHandler =
                new RotationFileHandler(
                        "logs",
                        1,
                        ChronoUnit.HOURS
                );

        logger.addHandler(rotationHandler);

        logger.info("Сообщение с ротацией");


        // 4. MemoryHandler
        MemoryHandler memoryHandler =
                new MemoryHandler(
                        new ConsoleHandler(),
                        3
                );

        Logger memoryLogger =
                Logger.getLogger("memoryLogger");

        memoryLogger.addHandler(memoryHandler);

        memoryLogger.info("Сообщение 1");
        memoryLogger.info("Сообщение 2");

        System.out.println("Пока сообщения находятся в памяти");

        memoryLogger.info("Сообщение 3");

        System.out.println("Три сообщения автоматически отправились");

        // Принудительная отправка оставшихся сообщений
        memoryHandler.flush();


        // 5. Несколько обработчиков одновременно
        Logger multiLogger =
                Logger.getLogger("multiLogger");

        multiLogger.addHandler(new ConsoleHandler());
        multiLogger.addHandler(new FileHandler("multi.log"));

        multiLogger.info("Это сообщение идет сразу в два места");
    }
}