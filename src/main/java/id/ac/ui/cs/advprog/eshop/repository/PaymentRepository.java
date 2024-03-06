package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Repository
public class PaymentRepository {
    private List<Payment> payments = new ArrayList<>();

    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(order.getId(), method, order, paymentData);
        payments.add(payment);
        return payment;
    }
    public Payment getPayment(String paymentId) {
        for (Payment payment : payments) {
            if (payment.getId().equals(paymentId)) {
                return payment;
            }
        }
        return null;
    }
    public List<Payment> getAllPayments() {
        return payments;
    }
}