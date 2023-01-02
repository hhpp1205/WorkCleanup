package cleanup.work.workcleanup.controller;

import cleanup.work.workcleanup.controller.form.CarForm;
import cleanup.work.workcleanup.controller.form.CarSearchCond;
import cleanup.work.workcleanup.entity.Car;
import cleanup.work.workcleanup.repository.CarRepository;
import cleanup.work.workcleanup.repository.dto.CarDto;
import cleanup.work.workcleanup.service.CarService;
import cleanup.work.workcleanup.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService carService;
    private final CarRepository carRepository;


    @GetMapping("/list")
    public String list(CarSearchCond cond, Model model, Pageable pageable) {
        List<CarDto> cars = carService.getCarList(cond, pageable);
        Long totalCount = carRepository.getCount(cond);

        int endPage = (int) Math.ceil((pageable.getPageNumber() + 1) / 5.0) * 5;
        int beginPage = endPage - 4;
        int realEndPage = (int) (Math.ceil((double) totalCount) / pageable.getPageSize());
        if (endPage > realEndPage) endPage = realEndPage;

        model.addAttribute("cars", cars);
        model.addAttribute("cond", cond);
        model.addAttribute("endPage", endPage);
        model.addAttribute("beginPage", beginPage);
        model.addAttribute("pageNumber", pageable.getPageNumber());
        return "page/car/car-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("carForm", new CarForm());
        return "page/car/createCarForm";
    }

    @PostMapping("/new")
    public String create(CarForm form) {
        carService.createCar(form);
        return "redirect:/car/list";
    }

    @GetMapping("{carId}/edit")
    public String updateForm(@PathVariable Long carId, Model model) {
        CarForm carForm = carService.findCarById(carId);
        model.addAttribute("carForm", carForm);
        return "page/car/updateCarForm";
    }

    @PostMapping("{carId}/edit")
    public String updateForm(@PathVariable("carId") Long carId, CarForm form) {
        carService.updateCar(carId, form);
        return "redirect:/car/list";
    }

}
