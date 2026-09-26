package com.example.task02;

public class DiscountBill extends Bill {

    private int discountPercent;

    public DiscountBill(int discountPercent){
        this.discountPercent = discountPercent;
    }

    @Override
    public long getPrice() {
        long originalPrice = super.getPrice();
        return (int) ((100-discountPercent)*originalPrice);
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public long getDiscountValue(){
        return super.getPrice()-getPrice();
    }
}
