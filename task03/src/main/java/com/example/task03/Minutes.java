package com.example.task03;

public class Minutes implements TimeUnit {
    private final long amount;
    public Minutes(long amount) {
        this.amount = amount;
        //throw new UnsupportedOperationException();
    }

    @Override
    public long toMillis() {
        return amount * 1000 * 60;
        //throw new UnsupportedOperationException();
    }

    @Override
    public long toSeconds() {
        return amount * 60;
        //throw new UnsupportedOperationException();
    }

    @Override
    public long toMinutes() {
        return amount;
        //throw new UnsupportedOperationException();
    }

    @Override
    public long toHours(){
        return Math.round(amount / 60.0);
    }

    @Override
    public long getHours(){
        return toHours();
    }
}
