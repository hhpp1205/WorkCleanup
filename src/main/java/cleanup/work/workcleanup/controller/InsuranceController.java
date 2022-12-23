package cleanup.work.workcleanup.controller;

import cleanup.work.workcleanup.controller.form.InsuranceForm;
import cleanup.work.workcleanup.entity.Insurance;
import cleanup.work.workcleanup.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/insurance")
public class InsuranceController {

    private final InsuranceService insuranceService;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("insuranceForm", new InsuranceForm());
        return "page/insurance/createInsuranceForm";
    }

    @PostMapping("/new")
    public String create(InsuranceForm form) {
        insuranceService.createInsurance(form);
        return "redirect:/";
    }


}
