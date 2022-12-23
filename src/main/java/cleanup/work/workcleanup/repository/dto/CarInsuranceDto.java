package cleanup.work.workcleanup.repository.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CarInsuranceDto {

    private Long id;

    private String carType;             //차량종류
    private String carNumber;             //차량번호
    private LocalDateTime createDate;    //청구일자
    private LocalDateTime paymentDate;  //입금일자
    private Long bill;                  //청구액
    private Long amount;                //지급액
    private Long excess;                //면책금

    @QueryProjection
    public CarInsuranceDto(Long id, String carType, String carNumber, LocalDateTime createDate, LocalDateTime paymentDate, Long bill, Long amount, Long excess) {
        this.id = id;
        this.carType = carType;
        this.carNumber = carNumber;
        this.createDate = createDate;
        this.paymentDate = paymentDate;
        this.bill = bill;
        this.amount = amount;
        this.excess = excess;
    }
}
