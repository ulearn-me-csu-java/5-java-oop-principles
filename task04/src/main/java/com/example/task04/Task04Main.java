package com.example.task04;

import java.time.temporal.ChronoUnit;

public class Task04Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("app");
        logger.setLevel(Logger.Level.DEBUG);

        logger.clearHandler();

        logger.addHandler(new ConsoleHandler());
        logger.addHandler(new FileHandler("app.log"));

        logger.info("Сервер запущен, порт: %s", 5432);
        logger.error("Не удалось подключить пользователя %s", "admin12345");

        logger.addHandler(new RotationFileHandler("app_rotation", ".log", ChronoUnit.HOURS));
        logger.warning("Это сообщение попадёт в файл вида app_rotation_2026-09-20_19.log");

        MemoryHandler memoryHandler = new MemoryHandler(new ConsoleHandler(), 5);
        Logger batchLogger = Logger.getLogger("batch");
        batchLogger.clearHandler();
        batchLogger.addHandler(memoryHandler);

        for (int i = 0; i <= 3; i++) {
            batchLogger.debug("Сообщение №%d", i);
        }
        System.out.println("Явный flush");
        memoryHandler.flush();
        System.out.println("Неявный flush");
        for (int i = 4; i <= 8; i++) {
            batchLogger.debug("Сообщение №%d", i);
        }
    }
}
