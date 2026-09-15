package com.example.task04;

import java.time.temporal.ChronoUnit;

public class Task04Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("app");

        // Пишем в консоль
        logger.addHandler(new ConsoleHandler());

        // Пишем в файл
        logger.addHandler(new FileHandler("app.log"));

        // Ротация по часам
        logger.addHandler(new RotationFileHandler("rotated", ChronoUnit.HOURS));

        // Буферизация: накапливаем 3 сообщения, потом разом в консоль
        MemoryHandler memory = new MemoryHandler(new ConsoleHandler(), 3);
        logger.addHandler(memory);

        logger.info("Сообщение 1");
        logger.warning("Сообщение 2");
        logger.error("Сообщение 3"); // тут сработает flush, и все 3 уйдут в консоль
    }
}