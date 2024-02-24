package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CarControllerTest {

    @Mock
    private CarService carService;

    @Mock
    private Model model;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCarPage() {
        String viewName = carController.createCarPage(model);
        assertEquals("CreateCar", viewName);
        verify(model).addAttribute(eq("car"), any(Car.class));
    }

    @Test
    void testCreateCarPost() {
        Car car = new Car();
        String viewName = carController.createCarPost(car, model);
        assertEquals("redirect:list", viewName);
        verify(carService).create(car);
    }

    @Test
    void testCarListPage() {
        List<Car> carList = new ArrayList<>();
        when(carService.findAll()).thenReturn(carList);

        String viewName = carController.carListPage(model);
        assertEquals("CarList", viewName);
        verify(model).addAttribute("cars", carList);
    }

    @Test
    void testEditCarPage() {
        String carId = "eef30a73-dcec-4aa4-84f3-50232889bd79";
        Car car = new Car();
        car.setCarId(carId);
        when(carService.findById(carId)).thenReturn(car);

        String viewName = carController.editCarPage(carId, model);
        assertEquals("EditCar", viewName);
        verify(model).addAttribute("car", car);
    }

    @Test
    void testEditCarPost() {
        String carId = "eef30a73-dced-4aa4-84f3-50232889bd79";
        Car editedCar = new Car();
        editedCar.setCarId(carId);

        String viewName = carController.editCarPost(editedCar, model);
        assertEquals("redirect:list", viewName);
        verify(carService).update(carId, editedCar);
    }

    @Test
    void testDeleteCar() {
        String carId = "eef30a73-dcef-4aa4-84f3-50232889bd79";
        Car car = new Car();
        car.setCarId(carId);
        when(carService.findById(carId)).thenReturn(car);

        String viewName = carController.deleteCar(carId);
        assertEquals("redirect:list", viewName);
        verify(carService).deleteCarById(carId);
    }
}
