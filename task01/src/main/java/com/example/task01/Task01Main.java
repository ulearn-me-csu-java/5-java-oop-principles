package com.example.task01;

public class Task01Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("myLogger");

        logger.debug("отладка");
        logger.info("информация");
        logger.warning("something weird happened");
        logger.error("ошибка");
    }
}
