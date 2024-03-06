package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class Payment {
    String id;
    String method;
    Order order;
    Map<String, String> paymentData;
    String status;

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this.id = id;
        if (order == null || paymentData == null || method == null) {
            throw new IllegalArgumentException();
        }
        this.order = order;
        this.setMethod(method);
        this.setPaymentData(paymentData);
    }

    public Payment(String id, String method, Order order, Map<String, String> paymentData, String status) {
        this(id, method, order, paymentData);
        this.setStatus(status);
    }

    public void setStatus(String status) {
        if (!(status.equals(PaymentStatus.SUCCESS.getValue()) || status.equals(PaymentStatus.REJECTED.getValue()))) {
            throw new IllegalArgumentException("Invalid payment status");
        } else {
            this.status = status;
            if (status.equals(PaymentStatus.SUCCESS.getValue())) {
                order.setStatus(OrderStatus.SUCCESS.getValue());
            } else {
                order.setStatus(OrderStatus.FAILED.getValue());
            }
        }
    }

    public void setMethod(String method) {
        if (method == null || !(method.equals(PaymentMethod.VOUCHER.getValue()) || method.equals(PaymentMethod.COD.getValue()))) {
            throw new IllegalArgumentException("Invalid payment method");
        }
        this.method = method;
    }

    public void setPaymentData(Map<String, String> paymentData) {
        boolean valid = true;
        if (paymentData.size() == 1) {
            String voucherCode = paymentData.get("voucherCode");
            if (voucherCode.length() == 16 && voucherCode.startsWith("ESHOP")) {
                int numCount = 0;
                for (int i = 0; i < 16; i++) {
                    if (Character.isDigit(voucherCode.charAt(i))) {
                        numCount++;
                    }
                }
                if (numCount != 8) {
                    valid = false;
                }
            } else {
                valid = false;
            }
        } else if (paymentData.get("address") == null ||
                paymentData.get("deliveryFee") == null ||
                paymentData.get("address").isEmpty() ||
                paymentData.get("deliveryFee").isEmpty()) {
            valid = false;
        }

        if (valid) {
            this.paymentData = paymentData;
            this.setStatus(PaymentStatus.SUCCESS.getValue());
        } else {
            throw new IllegalArgumentException("Invalid payment data");
        }
    }
}