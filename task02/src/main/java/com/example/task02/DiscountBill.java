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
        return price - (price * this.discount / 100);
    }

    public long getDiscountSum() {
        long basePrice = super.getPrice();
        return basePrice - getPrice();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Счет к оплате (скидка " + discount + "%)\n");
        sb.append("Сумма без скидки: ").append(super.getPrice()).append('\n');
        sb.append("Скидка: ").append(getDiscountSum()).append('\n');
        sb.append("Сумма к оплате: ").append(getPrice());
        return sb.toString();
    }
}
