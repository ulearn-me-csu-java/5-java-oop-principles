package com.example.task02;

public class DiscountBill extends Bill {
    private int discountPercent;

    public DiscountBill(int discountPercent) {
        super();
        this.discountPercent = discountPercent;
    }

    @Override
    public long getPrice() {
        long basePrice = super.getPrice();
        long discount = (long) (basePrice * discountPercent / 100.00);
        return basePrice - discount;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public long getDiscountAmount() {
        return super.getPrice() - this.getPrice();
    }
}
