package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Map<String, String> paymentData;
    private Order order;
    private List<Product> products;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();

        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620115");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);

        this.order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products, 1708560000L, "Safira Sudrajat");
    }

    @Test
    void testCreatePaymentEmptyOrder() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("544a9818-fae0-4f8d-8437-283509362d26", PaymentMethod.VOUCHER.getValue(), null, paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentEmptyPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("544a9818-fae0-4f8d-8437-283509362d26", PaymentMethod.VOUCHER.getValue(), order, null);
        });
    }

    @Test
    void testCreatePaymentEmptyPaymentMethod() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("544a9818-fae0-4f8d-8437-283509362d26", null, order, paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentInvalidPaymentMethod() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("544a9818-fae0-4f8d-8437-283509362d26", "INVALID", order, paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("544a9818-fae0-4f8d-8437-283509362d26", PaymentMethod.VOUCHER.getValue(), order, paymentData, PaymentStatus.SUCCESS.getValue());
        assertSame(order, payment.getOrder());
        assertEquals("544a9818-fae0-4f8d-8437-283509362d26", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("544a9818-fae0-4f8d-8437-283509362d26", PaymentMethod.VOUCHER.getValue(), order, paymentData, "INVALID");
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusToRejected() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("544a9818-fae0-4f8d-8437-283509362d26", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusToInvalidStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("544a9818-fae0-4f8d-8437-283509362d26", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherSuccess() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("544a9818-fae0-4f8d-8437-283509362d26", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        assertSame(order, payment.getOrder());
        assertEquals("544a9818-fae0-4f8d-8437-283509362d26", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherFail16Length() {
        paymentData.put("voucherCode", "ESHOP12345678");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("544a9818-fae0-4f8d-8437-283509362d26", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherFailESHOPStart() {
        paymentData.put("voucherCode", "ABCDE1234ABC5678");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("544a9818-fae0-4f8d-8437-283509362d26", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherFail8NumChar() {
        paymentData.put("voucherCode", "ESHOP1234ABCD567");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("544a9818-fae0-4f8d-8437-283509362d26", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithCodSuccess() {
        paymentData.put("address", "Jl. Raya Bogor");
        paymentData.put("deliveryFee", "20000");
        Payment payment = new Payment("544a9818-fae0-4f8d-8437-283509362d26", PaymentMethod.COD.getValue(), order, paymentData);
        assertSame(order, payment.getOrder());
        assertEquals("544a9818-fae0-4f8d-8437-283509362d26", payment.getId());
        assertEquals(PaymentMethod.COD.getValue(), payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithCodEmptyAddress() {
        paymentData.put("address", "");
        paymentData.put("deliveryFee", "20000");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("544a9818-fae0-4f8d-8437-283509362d26", PaymentMethod.COD.getValue(), order, paymentData);
        });
    }

    @Test
    void testCreatePaymentWithCodEmptyDeliveryFee() {
        paymentData.put("address", "Jl. Raya Bogor");
        paymentData.put("deliveryFee", "");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("544a9818-fae0-4f8d-8437-283509362d26", PaymentMethod.COD.getValue(), order, paymentData);
        });
    }
}
