package id.ac.ui.cs.advprog.eshop.model;

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
            new Payment("544a9818-fae0-4f8d-8437-283509362d26", "VOUCHER", null, paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentEmptyPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("544a9818-fae0-4f8d-8437-283509362d26", "VOUCHER", order, null);
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
    void testCreatePaymentWithVoucherSuccess() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("544a9818-fae0-4f8d-8437-283509362d26", "VOUCHER", order, paymentData);
        assertSame(order, payment.getOrder());
        assertEquals("544a9818-fae0-4f8d-8437-283509362d26", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals("SUCCESS", payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherFail16Length() {
        paymentData.put("voucherCode", "ESHOP12345678");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("544a9818-fae0-4f8d-8437-283509362d26", "VOUCHER", order, paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherFailESHOPStart() {
        paymentData.put("voucherCode", "ABCDE1234ABC5678");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("544a9818-fae0-4f8d-8437-283509362d26", "VOUCHER", order, paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherFail8NumChar() {
        paymentData.put("voucherCode", "ESHOP1234ABCD567");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("544a9818-fae0-4f8d-8437-283509362d26", "VOUCHER", order, paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithCodSuccess() {
        paymentData.put("address", "Jl. Raya Bogor");
        paymentData.put("deliveryFee", "20000");
        Payment payment = new Payment("544a9818-fae0-4f8d-8437-283509362d26", "COD", order, paymentData);
        assertSame(order, payment.getOrder());
        assertEquals("544a9818-fae0-4f8d-8437-283509362d26", payment.getId());
        assertEquals("COD", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals("SUCCESS", payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithCodEmptyAddress() {
        paymentData.put("address", "");
        paymentData.put("deliveryFee", "20000");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("544a9818-fae0-4f8d-8437-283509362d26", "COD", order, paymentData);
        });
    }

    @Test
    void testCreatePaymentWithCodEmptyDeliveryFee() {
        paymentData.put("address", "Jl. Raya Bogor");
        paymentData.put("deliveryFee", "");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("544a9818-fae0-4f8d-8437-283509362d26", "COD", order, paymentData);
        });
    }
}
