package com.example.task01;

public class Task01Main {
    public static void main(String[] args) {

        Logger logger1 = Logger.getLogger("test");
        Logger logger2 = Logger.getLogger("test");

        System.out.println(logger1 == logger2);

        logger1.debug("Проверка DEBUG");
        logger1.info("Привет, %s!", "мир");
        logger1.warning("Что-то странное произошло");
        logger1.error("Произошла ошибка");

        logger1.setLevel(Logger.Level.WARNING);

        logger1.debug("Это сообщение не должно появиться");
        logger1.info("И это тоже не должно появиться");
        logger1.warning("Это предупреждение появится");
        logger1.error("И эта ошибка появится");
    }
}