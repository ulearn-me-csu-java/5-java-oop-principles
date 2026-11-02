package com.example.task04;

public class Task04Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("MyLogger");

        logger.addHandler(new FileHandler("log.txt"));

        logger.info("Привет, логгер!");
        logger.warning("Предупреждение!");
        logger.error("Ошибка!");

    }
}
