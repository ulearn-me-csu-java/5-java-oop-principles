
package com.example.task02;

/**
 * Счет к оплате со скидкой
 */
public class DiscountBill extends Bill {

    // Размер скидки в процентах
    private final int discount;

    // Конструктор
    public DiscountBill(int discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Некорректная скидка");
        }

        this.discount = discount;
    }

    // Получение размера скидки в процентах
    public int getDiscount() {
        return discount;
    }

    // Получение абсолютного размера скидки
    public long getDiscountAmount() {
        return super.getPrice() - getPrice();
    }

    // Расчет стоимости с учетом скидки
    @Override
    public long getPrice() {
        return Math.round(super.getPrice() * (100 - discount) / 100.0);
    }
}
