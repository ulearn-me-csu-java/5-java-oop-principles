package com.example.task04;

import java.time.temporal.ChronoUnit;

public class Task04Main {
    public static void main(String[] args) {
        // создаем логгер
        Logger logger = Logger.getLogger("MyApp");
        logger.setLevel(Level.DEBUG); // Печатаем всё

        // добавляем вывод в консоль
        logger.addHandler(new ConsoleHandler());

        // добавляем вывод в файл "app.log"
        logger.addHandler(new FileHandler("app.log"));

        // ротация файлов новый файл каждые 2 секунды
        logger.addHandler(new RotationFileHandler("rotated", ChronoUnit.SECONDS));

        // буферизация копим 3 сообщения, потом разом выводим в консоль
        MemoryHandler memory = new MemoryHandler(new ConsoleHandler(), 3);
        logger.addHandler(memory);

        System.out.println("--- Начинаем логирование ---");

        logger.info("Первое сообщение");
        logger.warning("Второе сообщение");
        logger.error("Третье сообщение"); // На этом MemoryHandler сработает и выплюнет всё в консоль
    }
}