package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class PaymentSB {
    private static Object[][] contents = {
            {"paymentEntity", "Платеж"},
            {"paymentEntity.description", "Описание"},
            {"paymentEntity.amount", "Сумма"},
            {"paymentEntity.date", "Дата"}
    };

    public static Object[][] getContents() {
        return contents;
    }
}
