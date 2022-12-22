package cleanup.work.workcleanup.controller;

import cleanup.work.workcleanup.controller.form.CarInsuranceForm;
import cleanup.work.workcleanup.repository.CarInsuranceRepository;
import cleanup.work.workcleanup.repository.dto.CarDto;
import cleanup.work.workcleanup.repository.dto.InsuranceDto;
import cleanup.work.workcleanup.service.CarInsuranceService;
import cleanup.work.workcleanup.service.CarService;
import cleanup.work.workcleanup.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("cars-insurances")
@RequiredArgsConstructor
public class CarInsuranceController {

    private final CarInsuranceService carInsuranceService;
    private final InsuranceService insuranceService;
    private final CarInsuranceRepository carInsuranceRepository;


    @GetMapping("/new")
    public String carInsuranceForm(Model model) {
        List<CarDto> cars = carInsuranceService.getCarList();
        List<InsuranceDto> insurances = insuranceService.getInsuranceList();
        model.addAttribute("cars", cars);
        model.addAttribute("insurances", insurances);
        model.addAttribute("carInsuranceForm", new CarInsuranceForm());
        return "page/carsinsurances/createCarInsuranceForm";
    }

    @PostMapping("/new")
    public String createCarInsurance(CarInsuranceForm form,
                                     @RequestParam("carId") Long carId,
                                     @RequestParam("insuranceId")Long insuranceId) {

        carInsuranceService.createCarInsurance(carId, insuranceId, form);
        return "redirect:/cars-insurances/list";
    }

//    @GetMapping("/list")
//    public String list(Model model) {
//
//    }


}
