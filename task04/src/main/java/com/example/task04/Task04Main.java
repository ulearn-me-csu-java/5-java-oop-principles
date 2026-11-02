package com.example.task04;

import java.nio.file.Path;
import java.time.temporal.ChronoUnit;

public class Task04Main {
    public static void main(String[] args) {
        MessageHandler console = new ConsoleHandler();
        MessageHandler file = new FileHandler("logs/app.log");
        MessageHandler rotation = new RotationFileHandler(Path.of("logs"), "rotating", ChronoUnit.HOURS);
        // сообщения уходят в консоль порциями по 3
        MemoryHandler batched = new MemoryHandler(new ConsoleHandler(), 3);

        Logger logger = new Logger("main", LogLevel.DEBUG, console, file, rotation, batched);

        logger.debug("Запуск приложения");
        logger.info("Пользователь %s вошёл в систему", "alice");
        logger.warning("Мало памяти");
        logger.error("Ошибка подключения");

        logger.close(); // сбрасывает остаток из MemoryHandler
    }
}
