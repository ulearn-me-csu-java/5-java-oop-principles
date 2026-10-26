package com.example.task02;


public class DiscountBill extends Bill{
    private final int discount;

    public DiscountBill(int discount){
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("discount must be 0..100");
        }
        this.discount = discount;
    }
    public long getDiscountSum(){
        long price = super.getPrice();
        return price * discount/100;
    }
    public int getDiscount(){
        return discount;
    }

    @Override
    public long getPrice() {
        long price = super.getPrice();
        return price - price * discount/100;
    }

}
