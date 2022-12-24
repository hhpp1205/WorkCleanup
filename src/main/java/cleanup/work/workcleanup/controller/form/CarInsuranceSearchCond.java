package cleanup.work.workcleanup.controller.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CarInsuranceSearchCond {
    private String carType;
    private String carNumber;
    private Long insuranceId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate billDateStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate billDateEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDateStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDateEnd;

}
