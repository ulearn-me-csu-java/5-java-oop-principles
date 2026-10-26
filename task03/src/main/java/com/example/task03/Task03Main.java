package com.example.task03;

public class Task03Main {
    public static void main(String[] args) {
        TimeUnit unit1 = new Seconds(36000);
        TimeUnit unit2 = new Minutes(90);
        TimeUnit unit3 = new Hours(12);
        TimeUnit unit4 = new Milliseconds(1000000);
        printTimeUnit(unit1);
        printTimeUnit(unit2);
        printTimeUnit(unit3);
        printTimeUnit(unit4);
    }

    private static void printTimeUnit(TimeUnit unit) {
        System.out.println(String.format("Milliseconds: %d", unit.toMillis()));
        System.out.println(String.format("Seconds:      %d", unit.toSeconds()));
        System.out.println(String.format("Minutes:      %d", unit.toMinutes()));
        System.out.println(String.format("Hours: %d", unit.getHours()));
    }
}
