package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();

        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);

        List<Order> orders = new ArrayList<>();
        Order order1 = new Order("544a9818-fae0-4f8d-8437-283509362d26",
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("190a9818-fae0-4f8d-8437-228709362d26",
                products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);

        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("544a9818-fae0-4f8d-8437-283509362d26", "VOUCHER", orders.getFirst(), paymentDataVoucher);
        payments.add(payment1);

        Map<String, String> paymentDataCashOnDelivery = new HashMap<>();
        paymentDataCashOnDelivery.put("address", "Jl. Raya Bogor");
        paymentDataCashOnDelivery.put("deliveryFee", "20000");
        Payment payment2 = new Payment("190a9818-fae0-4f8d-8437-228709362d26", "COD", orders.get(1), paymentDataCashOnDelivery);
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        Payment payment = payments.getFirst();
        Payment result = paymentRepository.addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());

        Payment findResult = paymentRepository.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
    }

    @Test
    void testGetPaymentIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());
        }

        Payment findResult = paymentRepository.getPayment(payments.getFirst().getId());
        assertEquals(payments.getFirst().getId(), findResult.getId());
        assertEquals(payments.getFirst().getMethod(), findResult.getMethod());
        assertEquals(payments.getFirst().getPaymentData(), findResult.getPaymentData());
        assertEquals(payments.getFirst().getStatus(), findResult.getStatus());
    }

    @Test
    void testGetPaymentIfIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());
        }

        Payment findResult = paymentRepository.getPayment("zczc");
        assertNull(findResult);
    }

    @Test
    void testGetAllPayments() {
        for (Payment payment : payments) {
            paymentRepository.addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());
        }

        List<Payment> findResult = paymentRepository.getAllPayments();
        for (int i = 0; i < findResult.size(); i++) {
            assertEquals(payments.get(i).getId(), findResult.get(i).getId());
        }
    }
}