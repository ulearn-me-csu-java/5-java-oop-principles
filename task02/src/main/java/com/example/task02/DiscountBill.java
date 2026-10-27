package com.example.task02;

public class DiscountBill extends Bill {
    private final int discountPercent;

    public DiscountBill(int discountPercent) {
        if (discountPercent < 0 || discountPercent > 100)
            throw new IllegalArgumentException("Скидка должна быть от 0% до 100%!");
        this.discountPercent = discountPercent;
    }
    public int getDiscountPercent() {
        return discountPercent;
    }

    public long getAbsoluteDiscount() {
        return super.getPrice() * discountPercent / 100;
    }

    @Override
    public long getPrice() {
        return super.getPrice() - getAbsoluteDiscount();
    }
}