package com.example.task01;

public class Task01Main
{
    public static void main(String[] args)
    {
        Logger logger = Logger.getLogger("myLogger");

        Logger sameLogger = Logger.getLogger("myLogger");
        System.out.println("Same instance: " + (logger == sameLogger));
        System.out.println("Name: " + logger.getName());

        logger.debug("debug message");
        logger.info("info message");
        logger.warning("warning message");
        logger.error("error message");

        logger.info("user %s logged in from %s", "alice", "10.0.0.1");
        logger.warning("slow response: %d ms", 1500);

        logger.setLevel(Logger.Level.WARNING);
        System.out.println("Level: " + logger.getLevel());

        logger.debug("hidden debug message");
        logger.info("hidden info message");
        logger.warning("visible warning message");
        logger.error("visible error message");

        // Прямой вызов log
        logger.log(Logger.Level.ERROR, "direct error message");
        logger.log(Logger.Level.INFO, "hidden %s", "info");
        logger.log(Logger.Level.ERROR, "direct %s with %d", "template", 42);
    }
}