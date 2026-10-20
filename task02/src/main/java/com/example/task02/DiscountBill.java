package com.example.task02;

public class DiscountBill extends Bill {
    private final int discount;

    public DiscountBill(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    public long getDiscountAmount() {
        return super.getPrice() * discount / 100;
    }

    @Override
    public long getPrice() {
        return super.getPrice() - getDiscountAmount();
    }

    @Override
    public String toString() {
        // Вызываем базовый toString(), который внутри вызовет наш переопределенный getPrice()
        // и покажет итоговую цену со скидкой.
        return super.toString() + " (скидка " + discount + "%)";
    }
}