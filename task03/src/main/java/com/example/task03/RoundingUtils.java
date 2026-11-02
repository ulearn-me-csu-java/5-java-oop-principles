package com.example.task03;

/**
 * Вспомогательные методы округления
 */
final class RoundingUtils {

    private RoundingUtils() {
    }

    /**
     * Делит a на d (d > 0) с округлением по обычным правилам:
     * дробная часть < 0.5 отбрасывается, >= 0.5 округляется вверх.
     */
    static long divideRounded(long a, long d) {
        long q = Math.floorDiv(a, d);
        long r = Math.floorMod(a, d);
        return r * 2 >= d ? q + 1 : q;
    }
}