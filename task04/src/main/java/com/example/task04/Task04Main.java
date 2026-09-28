package com.example.task04;

import java.time.temporal.ChronoUnit;

public class Task04Main {
    public static void main(String[] args) {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        MemoryHandler memoryHandler = new MemoryHandler(3, consoleHandler);

        Logger logger = new Logger("appLogger", consoleHandler, memoryHandler);
        logger.info("Сообщение 1");
        logger.warning("Сообщение 2");
        logger.error("Сообщение 3");

        RotationFileHandler rotationFileHandler = new RotationFileHandler("log", ChronoUnit.HOURS);
        Logger fileLogger = new Logger("fileLogger", rotationFileHandler);
        fileLogger.info("Запись в файл");
    }
}
