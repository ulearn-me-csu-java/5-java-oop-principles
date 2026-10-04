package com.example.task02;

public class DiscountBill extends Bill {

    private int discount;

    public DiscountBill(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public long getPrice() {
        return super.getPrice() * (100 - discount) / 100;
    }

    public long getDiscountAmount() {
        return super.getPrice() - getPrice();
    }
}