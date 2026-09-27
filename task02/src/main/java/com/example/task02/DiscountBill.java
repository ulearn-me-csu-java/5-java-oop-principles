package com.example.task02;

public class DiscountBill extends Bill{
    private int discount; // 0-100
    public DiscountBill(int discount){
        this.discount = discount;
    }
    @Override
    public long getPrice(){
        long price = super.getPrice();
        return price - price * discount / 100;
    }
    public int getDiscount() {
        return this.discount;
    }
    public long getAbsoluteValueDisc(){
        return super.getPrice() - getPrice();
    }
}
