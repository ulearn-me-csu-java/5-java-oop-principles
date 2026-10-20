package com.example.task01;

public class Task01Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("myLogger");
        logger.setLevel(Logger.Level.DEBUG);

        logger.debug("Отладочное сообщение");
        logger.info("Информация: %s", "всё в порядке");
        logger.warning("Что-то странное произошло");
        logger.error("Ошибка: код %d", 42);

        // Проверка singleton
        Logger same = Logger.getLogger("myLogger");
        System.out.println("Один и тот же экземпляр? " + (logger == same));

        // Фильтрация
        logger.setLevel(Logger.Level.ERROR);
        logger.info("Это не напечатается");
        logger.error("А это напечатается");
    }
}