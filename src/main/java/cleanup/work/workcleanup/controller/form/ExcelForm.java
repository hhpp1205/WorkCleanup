package cleanup.work.workcleanup.controller.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ExcelForm {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private ExcelType excelType;        //Excel종류 (CAR, CARINSURANCE)

    private String insuranceName;       // 추출 할 보험이름
}
