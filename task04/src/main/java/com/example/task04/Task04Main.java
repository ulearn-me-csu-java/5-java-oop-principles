package com.example.task04;

public class Task04Main {
    public static void main(String[] args) {
        Logger l = new Logger("Биба");

        l.addHandler(new ConsoleHandler());
        l.addHandler(new FileHandler());
        l.info("Привет");
        l.error("ош");

        MemoryHandler memoryHandler = new MemoryHandler(3, new ConsoleHandler());

        Logger memLogger = new Logger("БуферЛоггер", memoryHandler);

        memLogger.info("Сообщение 1");

        memLogger.info("Сообщение 2");

        memLogger.info("Сообщение 3");

        memLogger.warning("Сообщение 4");
        memoryHandler.flush();
    }
}
