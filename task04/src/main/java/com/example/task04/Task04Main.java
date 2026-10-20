package com.example.task04;

import java.time.temporal.ChronoUnit;

public class Task04Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("AdvancedLogger");

        // 1. Вывод в консоль
        logger.addHandler(new ConsoleHandler());

        // 2. Вывод в файл (app.log)
        logger.addHandler(new FileHandler("logs/app.log"));

        // 3. Ротация файлов каждый час (файлы вида logs/rotated_2023-10-25_15.txt)
        logger.addHandler(new RotationFileHandler("logs/rotated", ChronoUnit.HOURS));

        // 4. Буферизация: отправляем в консоль, но только пачками по 3 сообщения
        MemoryHandler memoryHandler = new MemoryHandler(new ConsoleHandler(), 3);
        logger.addHandler(memoryHandler);

        logger.info("Сообщение 1");
        logger.warning("Сообщение 2");
        logger.error("Сообщение 3"); // Тут MemoryHandler сработает, и в консоль выведутся все 3 сообщения пачкой

        logger.debug("Сообщение 4");
        // Принудительно очищаем буфер (выведет Сообщение 4)
        memoryHandler.flush();
    }
}