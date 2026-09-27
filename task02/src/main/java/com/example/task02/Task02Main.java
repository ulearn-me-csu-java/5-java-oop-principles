package com.example.task02;

public class Task02Main {

    public static void main(String[] args) {
        Item apple = new Item("apple", 10);
        Item bread = new Item("bread", 5);

        DiscountBill bill = new DiscountBill(10);
        bill.add(apple,50);
        bill.add(bread, 3);

        System.out.println("Скидка: " + bill.getDiscountPrecent() + "%");
        System.out.println("Экономия: " + bill.getDiscount());
        System.out.println("К оплате: " + bill.getPrice());
    }
}
