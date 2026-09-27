package com.example.task02;

public class DiscountBill extends Bill {
    private final int discountPrecent;

    public DiscountBill(int discountPrecent) {
        this.discountPrecent = discountPrecent;
    }

    @Override
    public long getPrice() {
        long basePrice = super.getPrice();
        return basePrice - basePrice * discountPrecent / 100;
    }

    public int getDiscountPrecent() {
        return discountPrecent;
    }

    public long getDiscount() {
        long basePrice = super.getPrice();
        return basePrice - getPrice();
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nСкидка: " + getDiscount() + "%"
                + "\nИтого со скидкой: " + getPrice();
    }
}
