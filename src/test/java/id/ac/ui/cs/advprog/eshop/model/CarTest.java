package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CarTest {
    Car car;
    @BeforeEach
    void setUp() {
        this.car = new Car();
        this.car.setCarId("20c179a8-2c52-4f91-87d6-c3459f13aed7");
        this.car.setCarName("Mobil Cap Udin");
        this.car.setCarQuantity(100);
    }
    @Test
    void testGetCarId() {
        assertEquals("20c179a8-2c52-4f91-87d6-c3459f13aed7", this.car.getCarId());
    }

    @Test
    void testGetCarName() {
        assertEquals("Mobil Cap Udin", this.car.getCarName());
    }

    @Test
    void testGetCarQuantity() {
        assertEquals(100, this.car.getCarQuantity());
    }
}
