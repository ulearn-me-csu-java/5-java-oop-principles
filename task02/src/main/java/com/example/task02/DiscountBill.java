package com.example.task02;

import java.util.ArrayList;
import java.util.List;

public class DiscountBill extends Bill{
    private final int discount;

    public DiscountBill(int discount) {
        this.discount = discount;
    }

    public long  absoluteValueOfDiscount(){
        return (super.getPrice() * discount) / 100;
    }
    public int getDiscount() {
        return discount;
    }

    @Override
    public long getPrice() {
        return super.getPrice() - absoluteValueOfDiscount();
    }
    @Override
    public String toString() {
        return super.toString() +
                "\nСкидка: " + discount + "%" +
                "\nРазмер скидки: " + absoluteValueOfDiscount();
    }

}
