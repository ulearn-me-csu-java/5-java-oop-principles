package com.example.task01;

public class Task01Main {
    public static void main(String[] args) {

        Logger logger = Logger.getLogger("myLogger");

        logger.debug("Отладочное сообщение");
        logger.info("Информационное сообщение");
        logger.warning("Предупреждение");
        logger.error("Ошибка");

        logger.info("Привет, %s!", "Георгий");

        logger.setLevel(Logger.Level.WARNING);

        logger.debug("Это сообщение не появится");
        logger.info("Это сообщение тоже не появится");
        logger.warning("Это предупреждение появится");
        logger.error("Эта ошибка появится");

        Logger logger2 = Logger.getLogger("myLogger");

        System.out.println(logger == logger2);
    }
}