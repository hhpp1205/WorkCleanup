package cleanup.work.workcleanup.controller.form;

import cleanup.work.workcleanup.converter.LocalDateAndLocalDateTimeConverter;
import lombok.Builder;
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
    private LocalDate billDate;    //청구일자
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;  //입금일자

    private Long bill;                  //청구액
    private Long amount;                //지급액
    private Long excess;                //면책금

    private Long carId;
    private Long insuranceId;

    @Builder
    public CarInsuranceForm(Long id, LocalDate billDate, LocalDate paymentDate, Long bill, Long amount, Long excess, Long carId, Long insuranceId) {
        this.id = id;
        this.billDate = billDate;
        this.paymentDate = paymentDate;
        this.bill = bill;
        this.amount = amount;
        this.excess = excess;
        this.carId = carId;
        this.insuranceId = insuranceId;
    }
}
