package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    VOUCHER("VOUCHER"),
    COD("COD");

    private final String value;
    private PaymentMethod(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}