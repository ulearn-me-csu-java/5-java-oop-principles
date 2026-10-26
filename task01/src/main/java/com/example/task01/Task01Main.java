package com.example.task01;

public class Task01Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getLogger("test");
        Logger logger2 = Logger.getLogger("test");
        System.out.println(logger1 == logger2);
        logger1.log(Logger.Level.INFO, "its logger text" );
        logger1.error("error number: %s", 123);
        logger2.warning("123");

    }
}
