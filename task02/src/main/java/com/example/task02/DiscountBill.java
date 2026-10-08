package com.example.task02;

public class DiscountBill extends Bill {
    private double discount;

    public double getDiscount() {
        return discount;
    }
    public long getDiscountProcent() {
        return Math.round(discount*100);
    }
    @Override
    public long getPrice()
    {
        return Math.round(super.getPrice() * discount);
    }
    public long absoluteDiscount()
    {
        return super.getPrice() - getPrice();
    }
}
