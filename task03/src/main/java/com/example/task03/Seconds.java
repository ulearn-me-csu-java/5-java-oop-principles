package com.example.task03;

/**
 * Интервал в секундах
 */
public class Seconds extends AbstractTimeUnit {

    public Seconds(long amount) {
        super(amount, 1000);
    }
}