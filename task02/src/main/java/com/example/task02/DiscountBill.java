package com.example.task02;

/**
 * наследуется от базового Bill и добавляет скидку
 */
public class DiscountBill extends Bill {

    // скидка в процентах
    private final int discountPercent;

    /**
     * конструктор счета со скидкой
     */
    public DiscountBill(int discountPercent) {
        // Вызываем конструктор родителя (Bill)
        super();
        this.discountPercent = discountPercent;
    }

    /**
     * возвращает размер скидки в процентах
     */
    public int getDiscountPercent() {
        return discountPercent;
    }

    /**
     * Возвращает значение скидки в деньгах
     */
    public long getDiscountAmount() {
        // super.getPrice() цена без скидки
        // getPrice()  цена со скидкой, переопределенный метод
        return super.getPrice() - getPrice();
    }

    /**
     * переопределяем метод получения итоговой стоимости
     */
    @Override
    public long getPrice() {
        // получаем базовую сумму без скидки (вызываем метод родителя)
        long originalPrice = super.getPrice();

        // вычисляем сумму скидки в деньгах
        long discountSum = (long) (originalPrice * (discountPercent / 100.0));

        // 3. Возвращаем итоговую сумму
        return originalPrice - discountSum;
    }

    /**
     * переопределяем toString, чтобы в чеке была информация о скидке
     */
    @Override
    public String toString() {
        // берем базовую строку из родителя
        // проще всего вызвать super.toString() и добавить инфу о скидке
        return super.toString() +
                "\nСкидка: " + discountPercent + "%" +
                "\nРазмер скидки: " + getDiscountAmount() +
                "\nИтого со скидкой: " + getPrice();
    }
}