package com.example.task04;

import java.time.temporal.ChronoUnit;

public class Task04Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("MyLogger");

        // 1. Обычный вывод в консоль
        logger.addHandler(new ConsoleHandler());

        // 2. Вывод в файл app.log
        logger.addHandler(new FileHandler("app.log"));

        // 3. Вывод в файл с ротацией каждый час (например, app_hourly_2023-10-27_14-00.log)
        logger.addHandler(new RotationFileHandler("app_hourly", ChronoUnit.HOURS));

        // 4. MemoryHandler: копит 5 сообщений, потом скидывает в консоль
        MemoryHandler memoryHandler = new MemoryHandler(new ConsoleHandler(), 5);
        logger.addHandler(memoryHandler);

        // Тестируем!
        logger.info("Это сообщение уйдет во все обработчики");
        logger.debug("Это отладка");
        logger.error("А это ошибка!");

        // Принудительно сбрасываем буфер памяти (если там меньше 5 сообщений, они не выведутся сами)
        memoryHandler.flush();
    }
}
