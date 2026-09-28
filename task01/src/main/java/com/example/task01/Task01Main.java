package com.example.task01;

public class Task01Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getLogger("test");
        Logger logger2 = Logger.getLogger("test");
        System.out.println(logger1 == logger2);

        Logger logger3 = Logger.getLogger("infoTester");
        logger3.setLevel(Level.INFO);
        logger3.info("Чепуха");
    }
}
