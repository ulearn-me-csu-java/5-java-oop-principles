package com.example.task02;

public class DiscountBill extends Bill{
    private final int discount;

    public DiscountBill(int discount) {
        super();
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    public long getPrice() {
        long basePrice = super.getPrice();
        return basePrice - (basePrice * discount / 100);
    }

    public long getAbsoluteDiscount() {
        return super.getPrice() - getPrice();
    }
}
