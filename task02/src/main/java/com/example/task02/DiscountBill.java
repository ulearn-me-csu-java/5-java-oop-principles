package com.example.task02;

public class DiscountBill extends Bill {

    private final int discountPercent;

    public DiscountBill(int discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Скидка должна быть в диапазоне 0..100, получено: " + discountPercent);
        }
        this.discountPercent = discountPercent;
    }

    @Override
    public long getPrice() {
        long price = super.getPrice();
        return (price * (100 - discountPercent) + 50) / 100;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public long getDiscountAmount() {
        return super.getPrice() - getPrice();
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nСкидка: " + discountPercent + "% (-" + getDiscountAmount() + ")"
                + "\nСумма без скидки: " + super.getPrice();
    }
}
