package com.example.task02;

public class DiscountBill extends Bill {
    private final int discount;

    public DiscountBill(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public long getPrice() {
        long price = super.getPrice();
        return price - (price * discount / 100);
    }

    public long getAbsoluteDiscount() {
        return super.getPrice() - getPrice();
    }
}
