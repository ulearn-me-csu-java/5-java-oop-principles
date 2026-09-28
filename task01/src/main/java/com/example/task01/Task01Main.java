package com.example.task01;

public class Task01Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getLogger("test");
        Logger logger2 = Logger.getLogger("test");
        System.out.println( logger1 == logger2 );


        System.out.println("=== 1. Демонстрация 'Одиночки по имени' ===");
        Logger mainLogger = Logger.getLogger("Main");
        Logger dbLogger = Logger.getLogger("Database");

        Logger anotherMainLogger = Logger.getLogger("Main");

        System.out.println("mainLogger == anotherMainLogger: " + (mainLogger == anotherMainLogger)); // true
        System.out.println("mainLogger == dbLogger: " + (mainLogger == dbLogger)); // false
        System.out.println();

        System.out.println("=== 2. Логирование со стандартным уровнем (DEBUG) ===");
        mainLogger.debug("Это отладочное сообщение");
        mainLogger.info("Это информационное сообщение");
        dbLogger.warning("База данных почти заполнена");
        dbLogger.error("Ошибка подключения к базе данных");
        System.out.println();

    }
}
