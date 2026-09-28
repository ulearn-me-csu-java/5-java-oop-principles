package com.example.task04;

import java.nio.file.Path;

public class Task04Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("myLogger");
        logger.addHandler(new ConsoleHandler());

        MemoryHandler memory = new MemoryHandler(new ConsoleHandler(), 3);
        logger.addHandler(memory);

        logger.info("Hello, world!");
        logger.warning("Something weird happened");
        logger.error("Error occurred");

        logger.debug("Debug 1");
        logger.debug("Debug 2");
        logger.debug("Debug 3");

        logger.flush();
        logger.close();
    }
}
