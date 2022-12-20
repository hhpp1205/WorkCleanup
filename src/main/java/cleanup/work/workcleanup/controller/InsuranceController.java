package cleanup.work.workcleanup.controller;

import cleanup.work.workcleanup.controller.form.InsuranceForm;
import cleanup.work.workcleanup.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/insurances")
public class InsuranceController {

    private InsuranceService insuranceService;

    @GetMapping("new")
    public String createForm() {
        return "page/insurances/createInsuranceForm";
    }

    @PostMapping("new")
    public String create(InsuranceForm form) {
        insuranceService.createInsurance(form);
        return "page/insurances/lsit";
    }


}
