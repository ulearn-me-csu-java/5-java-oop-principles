package com.example.task02;

public class DiscountBill extends Bill{
    private double discount;

    public DiscountBill(double discount){
        this.discount = discount;
    }

    public double getDiscount(){return this.discount;}

    public void setDiscount(double discount){
        this.discount = discount;
    }

    //без скидки
    public long getFullPrice(){
        return super.getPrice();
    }

    // сама скидка
    public long getDiscounts(){
        long price = super.getPrice();
        return (long) (price * discount / 100);
    }
    @Override
    //со скидкой
    public long getPrice(){
        long price = super.getPrice();
        long priceWithDiscount = getDiscounts();
        return price - priceWithDiscount;
    }




}
