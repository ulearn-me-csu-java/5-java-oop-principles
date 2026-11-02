package com.example.task01;

public class Task01Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("myLogger");
        logger.debug("debug message");
        logger.info("user %s logged in", "admin");
        logger.warning("something weird happened");
        logger.setLevel(Level.ERROR);
        logger.warning("this will not be printed");
        logger.error("error code: %d", 42);
        System.out.println(Logger.getLogger("myLogger") == logger); // true
    }
}
