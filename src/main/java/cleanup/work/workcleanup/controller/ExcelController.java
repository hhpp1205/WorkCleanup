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

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/excel")
@RequiredArgsConstructor
public class ExcelController {

    private static final List<String> INSURANCE_SHEET_NAME = Arrays.asList("삼성", "현대", "DB", "KB", "롯데", "한화", "흥국", "메리츠", "AXA", "하나손보", "택공", "개택공", "화물", "렌터카공제");
    private final ExcelService excelService;
    private final InsuranceRepository insuranceRepository;

    @GetMapping("/new")
    public String excelForm(Model model) {

        List<Insurance> insuranceList = insuranceRepository.getInsuranceList();
        model.addAttribute("insuranceNames", INSURANCE_SHEET_NAME);

        return "page/excel/excelForm";
    }

    @PostMapping("/new")
    public String excelDown(ExcelForm excelForm) {
        if(excelForm.getExcelType().equals(ExcelType.CARINSURANCE)) excelService.getCarInsuranceExcel(excelForm);
        else excelService.getCarExcel(excelForm);

        return "redirect:/";
    }


}
