package com.example.task02;

public class DiscountBill extends Bill {
    private final int discount;

    public DiscountBill(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    public long getDiscountAbs() {
        return super.getPrice() - getPrice();
    }

    @Override
    public long getPrice() {
        long originalPrice = super.getPrice();
        return originalPrice - (originalPrice * discount / 100);
    }
}
