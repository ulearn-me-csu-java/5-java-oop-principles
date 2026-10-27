package com.example.task04;

import java.time.temporal.ChronoUnit;

public class Task04Main {
    public static void main(String[] args) {
        Logger logger = new Logger("test1");

        RotationFileHandler rfh = new RotationFileHandler(logger.getName(), ChronoUnit.MINUTES);

        logger.addHandler(rfh);

        logger.log(Level.INFO, "Мой первый лог");
        sleep(1000);
        logger.log(Level.INFO, "Мой второй лог");
        sleep(1000);
        logger.log(Level.INFO, "Мой третий лог");
        logger.log(Level.INFO, "Мой четвертый лог");
        logger.log(Level.INFO, "Мой пятый лог");
        sleep(1000);
        logger.log(Level.INFO, "Мой шестой лог");
        logger.log(Level.INFO, "Мой седьмой лог");
        logger.log(Level.INFO, "Мой восьмой лог");
        logger.log(Level.INFO, "Мой девятый лог");
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }
}
