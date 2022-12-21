package cleanup.work.workcleanup.controller;

import cleanup.work.workcleanup.repository.dto.CarDto;
import cleanup.work.workcleanup.repository.dto.InsuranceDto;
import cleanup.work.workcleanup.service.CarInsuranceService;
import cleanup.work.workcleanup.service.CarService;
import cleanup.work.workcleanup.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("cars-insurances")
@RequiredArgsConstructor
public class CarInsuranceController {

    private final CarInsuranceService carInsuranceService;
    private final CarService carService;
    private final InsuranceService insuranceService;


    @GetMapping("new")
    public String createCarInsurance(Model model) {
        List<CarDto> cars = carInsuranceService.getCarlist();
        List<InsuranceDto> insurances = insuranceService.getInsuranceList();
        model.addAttribute("cars", cars);
        model.addAttribute("insurances", insurances);
        return "page/carsinsurances/createCarInsuranceForm";
    }


}
