package cleanup.work.workcleanup.controller;

import cleanup.work.workcleanup.controller.form.CarInsuranceForm;
import cleanup.work.workcleanup.controller.form.CarInsuranceSearchCond;
import cleanup.work.workcleanup.entity.Insurance;
import cleanup.work.workcleanup.repository.CarInsuranceRepository;
import cleanup.work.workcleanup.repository.InsuranceRepository;
import cleanup.work.workcleanup.repository.dto.CarDto;
import cleanup.work.workcleanup.repository.dto.CarInsuranceDto;
import cleanup.work.workcleanup.repository.dto.InsuranceDto;
import cleanup.work.workcleanup.service.CarInsuranceService;
import cleanup.work.workcleanup.service.CarService;
import cleanup.work.workcleanup.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("car-insurance")
@RequiredArgsConstructor
public class CarInsuranceController {

    private final CarInsuranceService carInsuranceService;
    private final InsuranceService insuranceService;
    private final InsuranceRepository insuranceRepository;
    private final String path = "page/carinsurance/";


    @GetMapping("/new")
    public String carInsuranceForm(Model model) {
        List<CarDto> cars = carInsuranceService.getCarList();
        List<InsuranceDto> insurances = insuranceService.getInsuranceList();
        model.addAttribute("cars", cars);
        model.addAttribute("insurances", insurances);
        model.addAttribute("carInsuranceForm", new CarInsuranceForm());
        return path + "createCarInsuranceForm";
    }

    @PostMapping("/new")
    public String createCarInsurance(CarInsuranceForm form,
                                     @RequestParam("carId") Long carId,
                                     @RequestParam("insuranceId")Long insuranceId) {

        carInsuranceService.createCarInsurance(carId, insuranceId, form);
        return "redirect:/car-insurance/list";
    }

    @GetMapping("/list")
    public String list(Model model, CarInsuranceSearchCond cond) {
        List<CarInsuranceDto> carInsuranceList = carInsuranceService.getCarInsuranceList(cond);
        model.addAttribute("carInsuranceList", carInsuranceList);

        List<Insurance> insuranceList = insuranceRepository.getInsuranceList();
        model.addAttribute("insuranceList", insuranceList);
        return path + "car-insurance-list";
    }

    @GetMapping("{carInsuranceId}/edit")
    public String updateForm(@PathVariable Long carInsuranceId, Model model) {
        CarInsuranceForm carInsuranceForm = carInsuranceService.findByCarInsuranceId(carInsuranceId);
        model.addAttribute("carInsuranceForm", carInsuranceForm);

        List<CarDto> cars = carInsuranceService.getCarList();
        List<InsuranceDto> insurances = insuranceService.getInsuranceList();
        model.addAttribute("cars", cars);
        model.addAttribute("insurances", insurances);

        return path + "updateCarInsuranceForm";
    }

    @PostMapping("{carInsuranceId}/edit")
    public String updateCarInsurance(@PathVariable Long carInsuranceId, CarInsuranceForm carInsuranceForm) {
        carInsuranceService.updateCarInsurance(carInsuranceId, carInsuranceForm);
        int i = 30;


        return "redirect:/car-insurance/list";
    }


}
