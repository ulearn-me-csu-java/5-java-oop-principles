package com.example.task02;

/**
 * Счет к оплате с учетом скидки
 */
public class DiscountBill extends Bill {
    private final double discount; // скидка в процентах

    public DiscountBill(double discount) {
        this.discount = discount;
    }

    /**
     * @return размер скидки в процентах
     */
    public double getDiscountPercent() {
        return discount;
    }

    /**
     * @return абсолютное значение скидки
     */
    public long getDiscount() {
        return super.getPrice() - getPrice();
    }

    /**
     * @return итоговая стоимость с учетом скидки
     */
    @Override
    public long getPrice() {
        return Math.round(super.getPrice() * (100 - discount) / 100);
    }
}
