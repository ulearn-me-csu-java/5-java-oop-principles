package com.example.task02;

public class DiscountBill extends Bill{
    private double discount;
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public double getDiscount() {
        return discount;
    }

    public long getAbsoluteDiscount() {
        return super.getPrice() - this.getPrice();
    }

    @Override
    public long getPrice() {
        long basePrice = super.getPrice();

        return (long) (basePrice * (1 - discount / 100.0));
    }
}
