package com.example.task02;

public class DiscountBill extends Bill {
    private int discount;

    public DiscountBill(int discount) {
        this.discount = discount;
    }

    @Override
    public long getPrice() {
        long full = super.getPrice();
        full = (long) (full - (full * discount / 100.0));
        return full;
        /*
        Также должен быть метод получения размера скидки (в процентах) и
        абсолютного значения скидки (разница между суммой и суммой со скидкой).
         */
    }

    public int getDiscountPercent() {
        return discount;
    }

    public long getAbsDiscount() {
        long result = super.getPrice() - getPrice();
        return result;
    }


}
