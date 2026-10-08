package com.example.task02;

public class Task02Main {
    public static void main(String[] args) {
        // ─── Тест 1: базовая скидка 10% ───────────────────────
        System.out.println("=== Тест 1: скидка 10%, 1000₽ ===");
        DiscountBill bill1 = new DiscountBill(10);
        bill1.add(new Item("Товар", 1000), 1);
        System.out.println("Полная сумма (super): " + new Bill() {{
            add(new Item("Товар", 1000), 1);
        }}.getPrice());
        System.out.println("Со скидкой: " + bill1.getPrice());
        System.out.println("Скидка %: " + bill1.getDiscountPercent());
        System.out.println("Скидка в деньгах: " + bill1.getAbsDiscount());
        System.out.println("toString:\n" + bill1);
        System.out.println();

        // ─── Тест 2: несколько товаров ────────────────────────
        System.out.println("=== Тест 2: скидка 10%, 180₽ ===");
        DiscountBill bill2 = new DiscountBill(10);
        bill2.add(new Item("Хлеб", 50), 2);   // 100
        bill2.add(new Item("Молоко", 80), 1); // 80
        System.out.println("Со скидкой: " + bill2.getPrice());          // ожидаем 162
        System.out.println("Скидка в деньгах: " + bill2.getAbsDiscount()); // ожидаем 18
        System.out.println();

        // ─── Тест 3: скидка 0% ────────────────────────────────
        System.out.println("=== Тест 3: скидка 0% ===");
        DiscountBill bill3 = new DiscountBill(0);
        bill3.add(new Item("Товар", 500), 2);
        System.out.println("Со скидкой: " + bill3.getPrice());          // ожидаем 1000
        System.out.println("Скидка в деньгах: " + bill3.getAbsDiscount()); // ожидаем 0
        System.out.println();

        // ─── Тест 4: скидка 100% ──────────────────────────────
        System.out.println("=== Тест 4: скидка 100% ===");
        DiscountBill bill4 = new DiscountBill(100);
        bill4.add(new Item("Товар", 300), 1);
        System.out.println("Со скидкой: " + bill4.getPrice());          // ожидаем 0
        System.out.println("Скидка в деньгах: " + bill4.getAbsDiscount()); // ожидаем 300
        System.out.println();

        // ─── Тест 5: пустой счёт ──────────────────────────────
        System.out.println("=== Тест 5: пустой счёт, скидка 50% ===");
        DiscountBill bill5 = new DiscountBill(50);
        System.out.println("Со скидкой: " + bill5.getPrice());          // ожидаем 0
        System.out.println("Скидка в деньгах: " + bill5.getAbsDiscount()); // ожидаем 0
        System.out.println();

        // ─── Тест 6: проблема округления ──────────────────────
        System.out.println("=== Тест 6: скидка 15%, 999₽ (проверка округления) ===");
        DiscountBill bill6 = new DiscountBill(15);
        bill6.add(new Item("Товар", 333), 3);  // 999
        System.out.println("Со скидкой: " + bill6.getPrice());          // ожидаем 850 (а не 849)
        System.out.println("Скидка в деньгах: " + bill6.getAbsDiscount()); // ожидаем 149 (а не 150)
        System.out.println("Реальная скидка: 149.85₽");
        System.out.println("→ потеряно копеек: " + (149.85 - bill6.getAbsDiscount()));
        System.out.println();

        // ─── Тест 7: проверка, что add суммирует количества ───
        System.out.println("=== Тест 7: добавление одного товара дважды ===");
        DiscountBill bill7 = new DiscountBill(0);
        Item item = new Item("Товар", 100);
        bill7.add(item, 1);
        bill7.add(item, 2);  // должно стать amount = 3
        System.out.println("Со скидкой: " + bill7.getPrice()); // ожидаем 300
        System.out.println("toString:\n" + bill7);
        System.out.println();

        // ─── Тест 8: проверка наследования через Bill ─────────
        System.out.println("=== Тест 8: полиморфизм ===");
        Bill asBill = new DiscountBill(20);
        asBill.add(new Item("Товар", 1000), 1);
        System.out.println("Через Bill-ссылку: " + asBill.getPrice()); // ожидаем 800
        // ↑ вызывается переопределённый getPrice() из DiscountBill
    }
}