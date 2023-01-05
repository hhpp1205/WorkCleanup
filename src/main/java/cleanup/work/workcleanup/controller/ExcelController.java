package cleanup.work.workcleanup.controller;

import cleanup.work.workcleanup.controller.form.ExcelForm;
import cleanup.work.workcleanup.controller.form.ExcelType;
import cleanup.work.workcleanup.entity.Insurance;
import cleanup.work.workcleanup.repository.InsuranceRepository;
import cleanup.work.workcleanup.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/excel")
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelService excelService;
    private final InsuranceRepository insuranceRepository;

    @GetMapping("/new")
    public String excelForm(Model model) {
        List<Insurance> insuranceList = insuranceRepository.getInsuranceList();
        model.addAttribute("insuranceList", insuranceList);

        return "page/excel/excelForm";
    }

    @PostMapping("/new")
    public String excelDown(ExcelForm excelForm) {
        if(excelForm.getExcelType().equals(ExcelType.CARINSURANCE)) excelService.getCarInsuranceExcel(excelForm);
        else excelService.getCarExcel(excelForm);

        return "redirect:/";
    }


}
