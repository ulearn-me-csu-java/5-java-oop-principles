package com.example.task02;

public class DiscountBill extends Bill {
    private final int discountPercent; // Размер скидки в процентах

    public DiscountBill(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    // Переопределяем метод получения цены
    @Override
    public long getPrice() {
        long originalPrice = super.getPrice(); // Получаем цену без скидки из базового класса
        // Считаем цену со скидкой (приводим к double для дробей, потом обратно в long)
        return (long) (originalPrice * (100.0 - discountPercent) / 100.0);
    }

    // Размер скидки в процентах
    public int getDiscountPercent() {
        return discountPercent;
    }

    // Абсолютная скидка (разница между ценой без скидки и со скидкой)
    public long getDiscountAmount() {
        return super.getPrice() - getPrice();
    }
}
