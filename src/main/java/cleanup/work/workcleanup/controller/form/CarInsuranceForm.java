package cleanup.work.workcleanup.controller.form;

import cleanup.work.workcleanup.converter.LocalDateAndLocalDateTimeConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CarInsuranceForm {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;    //청구일자
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;  //입금일자

    private Long bill;                  //청구액
    private Long amount;                //지급액
    private Long excess;                //면책금
}
