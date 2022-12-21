package cleanup.work.workcleanup.controller;

import cleanup.work.workcleanup.controller.form.CarForm;
import cleanup.work.workcleanup.entity.Car;
import cleanup.work.workcleanup.repository.CarRepository;
import cleanup.work.workcleanup.repository.dto.CarDto;
import cleanup.work.workcleanup.service.CarService;
import cleanup.work.workcleanup.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final CarRepository carRepository;


    @GetMapping("/list")
    public String list(Model model) {
        List<Car> cars = carRepository.findAllByOrderByCreateDateDesc();
        model.addAttribute("cars", cars);
        return "page/cars/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("carForm", new CarForm());
        return "page/cars/createCarForm";
    }

    @PostMapping("/new")
    public String create(CarForm form) {
        carService.createCar(form);
        return "redirect:/cars/list";
    }

    @GetMapping("{carId}/edit")
    public String updateForm(@PathVariable Long carId, Model model) {
        CarForm carForm = carService.findCarById(carId);
        model.addAttribute("carForm", carForm);
        return "page/cars/updateCarForm";
    }

    @PostMapping("{carId}/edit")
    public String updateForm(@PathVariable("carId") Long carId, CarForm form) {
        carService.updateCar(carId, form);
        return "redirect:/cars/list";
    }
}
