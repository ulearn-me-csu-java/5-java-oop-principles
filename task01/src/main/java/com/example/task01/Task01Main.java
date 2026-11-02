
package com.example.task01;

public class Task01Main {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger("myLogger");

        logger.debug("Это отладочное сообщение");
        logger.info("Программа запущена");
        logger.warning("Что-то пошло не так");
        logger.error("Произошла ошибка");

        System.out.println("--------------------");

        // Проверка форматирования
        logger.info("Пользователь %s, возраст %d", "Анна", 18);

        System.out.println("--------------------");

        // Проверка универсального метода
        logger.log(Logger.Level.WARNING, "Предупреждение через log");

        System.out.println("--------------------");

        // Проверка фильтрации
        logger.setLevel(Logger.Level.WARNING);

        logger.debug("Это сообщение не появится");
        logger.info("Это сообщение тоже не появится");
        logger.warning("Это предупреждение появится");
        logger.error("Эта ошибка появится");

        System.out.println("--------------------");

        // Проверка getLogger
        Logger logger1 = Logger.getLogger("test");
        Logger logger2 = Logger.getLogger("test");

        System.out.println(logger1 == logger2);
    }
}
