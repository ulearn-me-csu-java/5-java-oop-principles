package com.example.task04;

import java.time.temporal.ChronoUnit;

public class Task04Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("Task04");

        logger.addHandler(new ConsoleHandler());

        FileHandler fileHandler = new FileHandler("application.log");
        logger.addHandler(fileHandler);

        RotationFileHandler rotationFileHandler =
                new RotationFileHandler("rotating-log", ChronoUnit.HOURS);
        logger.addHandler(rotationFileHandler);

        MemoryHandler memoryHandler = new MemoryHandler(new ConsoleHandler(), 3);
        logger.addHandler(memoryHandler);

        logger.info("Application started");
        logger.debug("Debug message");
        logger.warning("Warning message");
        logger.error("Error message");

        memoryHandler.flush();

        fileHandler.close();
        rotationFileHandler.close();
    }
}