package com.example.task02;

public class DiscountBill extends Bill {

    private final double discount;

    public DiscountBill(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public long getPrice() {
        return Math.round(super.getPrice() * (1 - discount / 100));
    }

    public long getDiscountAmount() {
        return super.getPrice() - getPrice();
    }
}
