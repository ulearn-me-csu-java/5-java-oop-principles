package com.example.task01;

public class Task01Main {
    public static void main(String[] args) {
        System.out.println("--- Проверка 1: Синглтон ---");
        Logger log1 = Logger.getLogger("myApp");
        Logger log2 = Logger.getLogger("myApp");
        System.out.println("Это один и тот же объект? " + (log1 == log2)); // true

        System.out.println("\n--- Проверка 2: Фильтрация ---");
        Logger appLogger = Logger.getLogger("AppLogger");
        appLogger.setLevel(Level.WARNING); // Устанавливаем порог: печатать только WARNING и ERROR

        appLogger.debug("Это отладка, ее не должно быть видно");
        appLogger.info("Это инфа, ее тоже не должно быть видно");

        appLogger.warning("Осторожно! Это предупреждение.");
        appLogger.error("Критическая ошибка: %s", "База данных упала");
    }
}