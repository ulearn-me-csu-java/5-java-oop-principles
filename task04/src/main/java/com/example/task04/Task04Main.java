package com.example.task04;

import java.time.temporal.ChronoUnit;

public class Task04Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("app");

        logger.addHandler(new ConsoleHandler());

        logger.addHandler(new FileHandler("logs/app.log"));

        logger.addHandler(new RotationFileHandler("logs/rotate", ChronoUnit.HOURS));

        MemoryHandler memory = new MemoryHandler(new ConsoleHandler(), 5);
        logger.addHandler(memory);

        logger.info("первое");
        logger.warning("второе");
        logger.error("третье");

        memory.flush();
    }
}
