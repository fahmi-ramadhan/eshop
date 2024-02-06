package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ProductTest {
    Product product;
    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("20c179a8-2c52-4f91-87d6-c3459f13aed7");
        this.product.setProductName("Sampo Cap Udin");
        this.product.setProductQuantity(100);
    }
    @Test
    void testGetProductId() {
        assertEquals("20c179a8-2c52-4f91-87d6-c3459f13aed7", this.product.getProductId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Sampo Cap Udin", this.product.getProductName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.getProductQuantity());
    }
}
