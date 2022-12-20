package cleanup.work.workcleanup.controller;

import cleanup.work.workcleanup.service.CarInsuranceService;
import cleanup.work.workcleanup.service.CarService;
import cleanup.work.workcleanup.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cars-insurances")
@RequiredArgsConstructor
public class CarInsuranceController {

    private final CarInsuranceService carInsuranceService;
    private final CarService carService;
    private final InsuranceService insuranceService;


    @GetMapping("new")
    public String createCarInsurance(Model model) {
        carInsuranceService.getCarlist();
        return "page/carsinsurances/createCarInsuranceForm";
    }


}
