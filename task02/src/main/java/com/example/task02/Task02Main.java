package com.example.task02;

public class Task02Main {

    private static final Item ITEM1 = new Item("Товар 1", 10);
    private static final Item ITEM2 = new Item("Товар 2", 20);
    private static final Item ITEM3 = new Item("Товар 3", 30);
    private static final Item ITEM4 = new Item("Товар 4", 40);
    private static final Item ITEM5 = new Item("Товар 5", 50);
    private static final Item ITEM6 = new Item("Товар 6", 60);

    public static void main(String[] args) {
        Bill bill = new Bill();
        bill.add(ITEM1, 10);
        bill.add(ITEM3, 3);
        bill.add(ITEM6, 1);
        System.out.println(bill);
        bill.add(ITEM3, 3);
        System.out.println(bill);

        DiscountBill discountBill = new DiscountBill(10);
        discountBill.add(ITEM1, 10);
        discountBill.add(ITEM3, 3);
        discountBill.add(ITEM6, 1);
        System.out.println();
        System.out.println(discountBill);
        printSummary(discountBill);

        discountBill.add(ITEM3, 3);
        System.out.println();
        System.out.println(discountBill);
        printSummary(discountBill);

        System.out.println();
        try {
            new DiscountBill(101);
            System.out.println("Ошибка: скидка 101% не должна приниматься");
        } catch (IllegalArgumentException e) {
            System.out.println("Ожидаемое исключение: " + e.getMessage());
        }
    }

    private static void printSummary(DiscountBill bill) {
        long price = bill.getPrice();
        long discount = bill.getDiscountAmount();
        System.out.println("Скидка: " + bill.getDiscountPercent() + "%");
        System.out.println("Итог: " + price + ", абсолютная скидка: " + discount);
        System.out.println("Проверка: " + price + " + " + discount + " = " + (price + discount));
    }
}
