package com.example.task04;
import com.example.task04.Handlers.*;
import java.nio.file.Path;
import java.time.temporal.ChronoUnit;

public class Task04Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("app");

        MessageHandler console = new ConsoleHandler();
        
        MessageHandler file = new FileHandler(Path.of("app.log"));
        
        MessageHandler rotation = new RotationFileHandler("rotation_log", ".txt", ChronoUnit.HOURS);
        
        MessageHandler memory = new MemoryHandler(console, 5);

        logger.addHandler(console);
        logger.addHandler(file);
        logger.addHandler(rotation);
        // logger.addHandler(memory);
        
        logger.info("Сообщение 1");
        logger.error("Сообщение 2");
        logger.debug("Сообщение 3");
        logger.warning("Сообщение 4");
        logger.info("Сообщение 5");
        logger.error("Сообщение 6");
        logger.debug("Сообщение 7");
        logger.warning("Сообщение 8");
        logger.warning("Сообщение 9");
        logger.warning("Сообщение 10");
    }
}
