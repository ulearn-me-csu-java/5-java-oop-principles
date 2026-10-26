package com.example.task04;

import java.time.temporal.ChronoUnit;

public class Task04Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("logger");


        ConsoleHandler console = new ConsoleHandler();
        FileHandler file = new FileHandler("C:/Users/DF/IdeaProjects/5-java-oop-principles/task04/FileHandler.txt");
        RotationFileHandler rotate =  new RotationFileHandler("C:/Users/DF/IdeaProjects/5-java-oop-principles/task04/RotationHandler.txt", ChronoUnit.SECONDS);
        MemoryHandler mem = new MemoryHandler(new ConsoleHandler(), 4);

        logger.addHandler(console);
        logger.addHandler(file);
        logger.addHandler(rotate);
        logger.addHandler(mem);

        //for(int i = 0; i < 100000; i++) {
            logger.info("1");
            logger.warning("2");
            logger.error("3");
            logger.info("4");
            logger.info("5");
        //}

        logger.closeHandlers();
    }
}
