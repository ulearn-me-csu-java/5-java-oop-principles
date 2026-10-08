package com.example.task03;

public abstract class AbstractTimeUnit implements TimeUnit {
    private final long value;
    private final long millisPerUnit;

    protected AbstractTimeUnit(long value, long millisPerUnit) {
        this.value = value;
        this.millisPerUnit = millisPerUnit;
    }

    @Override
    public long getMilliseconds() {
        return (value * millisPerUnit);
    }

    @Override
    public long getSeconds() {
        return (Math.round((double) getMilliseconds() / 1000));
    }

    @Override
    public long getMinutes() {
        return (Math.round((double) getMilliseconds() / 60000));
    }

    @Override
    public long getHours() {
        return (Math.round((double) getMilliseconds() / 3600000));
    }
}
