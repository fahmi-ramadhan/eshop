package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Order> orders;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("544a9818-fae0-4f8d-8437-283509362d26",
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("190a9818-fae0-4f8d-8437-228709362d26",
                products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);

        payments = new ArrayList<>();

        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("544a9818-fae0-4f8d-8437-283509362d26", PaymentMethod.VOUCHER.getValue(), orders.getFirst(), paymentDataVoucher);
        payments.add(payment1);

        Map<String, String> paymentDataCashOnDelivery = new HashMap<>();
        paymentDataCashOnDelivery.put("address", "Jl. Raya Bogor");
        paymentDataCashOnDelivery.put("deliveryFee", "20000");
        Payment payment2 = new Payment("190a9818-fae0-4f8d-8437-228709362d26", PaymentMethod.COD.getValue(), orders.get(1), paymentDataCashOnDelivery);
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());

        Payment result = paymentService.addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());
        verify(paymentRepository, times(1)).addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testAddPaymentIfAlreadyExists() {
        Payment payment = payments.get(1);
        when(paymentRepository.getPayment(payment.getOrder().getId())).thenReturn(payment);

        paymentService.addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData());
        assertNull(paymentService.addPayment(payment.getOrder(), payment.getMethod(), payment.getPaymentData()));
        verify(paymentRepository, times(2)).getPayment(payment.getOrder().getId());
    }

    @Test
    void testGetPaymentIfIdFound() {
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).getPayment(payment.getId());

        Payment findResult = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals("VOUCHER", findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());

        verify(paymentRepository, times(1)).getPayment(payment.getId());
    }

    @Test
    void testGetPaymentIfIdNotFound() {
        doReturn(null).when(paymentRepository).getPayment("zczc");
        assertNull(paymentService.getPayment("zczc"));
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).getAllPayments();
        List<Payment> findResult = paymentService.getAllPayments();

        verify(paymentRepository, times(1)).getAllPayments();
        assertNotNull(findResult);
        assertEquals(payments.size(), findResult.size());
        assertTrue(findResult.containsAll(payments));
    }

    @Test
    void testSetSuccessStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("67d131db-1551-469b-bccd-b4593f8dbca6", "VOUCHER", orders.getFirst(), paymentData);

        assertEquals("SUCCESS", payment.getStatus());
        paymentService.setStatus(payment, "REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> paymentService.setStatus(payments.getFirst(), "MEOW"));
    }
}
