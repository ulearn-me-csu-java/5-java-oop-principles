package com.example.task04;

import java.time.temporal.ChronoUnit;

public class Task04Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("AppLogger");

        MessageHandler consoleHandler = new ConsoleHandler();

        MessageHandler fileHandler = new FileHandler("app.log");

        MessageHandler rotationHandler = new RotationFileHandler("rotation_%s.log", ChronoUnit.HOURS);

        MemoryHandler memoryHandler = new MemoryHandler(3, consoleHandler);

        logger.addHandler(consoleHandler);
        logger.addHandler(fileHandler);
        logger.addHandler(rotationHandler);

        logger.info("Приложение запущено");
        logger.warning("Предупреждение: ресурс используется интенсивно");
        logger.error("Ошибка при выполнении операции: код %d", 500);

        System.out.println("--- Демонстрация MemoryHandler ---");
        memoryHandler.handle("Буферизованное сообщение 1");
        memoryHandler.handle("Буферизованное сообщение 2");
        System.out.println("Сообщений в буфере: " + memoryHandler.getBufferSize());
        memoryHandler.handle("Буферизованное сообщение 3 (должен произойти автосброс)");
        System.out.println("Сообщений в буфере после сброса: " + memoryHandler.getBufferSize());

        memoryHandler.handle("Буферизованное сообщение 4 (ручной сброс)");
        memoryHandler.flush();
    }
}
