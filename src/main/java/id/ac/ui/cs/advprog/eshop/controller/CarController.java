package id.ac.ui.cs.advprog.eshop.controller;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/create")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "CreateCar";
    }

    @PostMapping("/create")
    public String createCarPost(@ModelAttribute Car car, Model model) {
        carService.create(car);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String carListPage(Model model) {
        List<Car> allCars = carService.findAll();
        model.addAttribute("cars", allCars);
        return "CarList";
    }

    @GetMapping("/edit/{carId}")
    public String editCarPage(@PathVariable String carId, Model model) {
        Car car = carService.findById(carId);
        model.addAttribute("car", car);
        return "EditCar";
    }

    @PostMapping("/edit")
    public String editCarPost(@ModelAttribute Car car, Model model) {
        carService.update(car.getCarId(), car);

        return "redirect:list";
    }

    @PostMapping("/delete")
    public String deleteCar(@RequestParam("carId") String carId) {
        carService.deleteCarById(carId);
        return "redirect:list";
    }
}
