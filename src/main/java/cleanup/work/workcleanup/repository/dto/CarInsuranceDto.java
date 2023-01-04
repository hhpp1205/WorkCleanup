package cleanup.work.workcleanup.repository.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CarInsuranceDto {

    private Long id;
    private Long carId;                  //carId
    private String carType;             //차량종류
    private String carNumber;             //차량번호
    private LocalDateTime billDate;    //청구일자
    private LocalDateTime paymentDate;  //입금일자
    private Long bill;                  //청구액
    private Long amount;                //지급액
    private Long excess;                //면책금
    private String insuranceName;       //보험명

    @QueryProjection
    public CarInsuranceDto(Long id, String carType, String carNumber, LocalDateTime billDate, LocalDateTime paymentDate, Long bill, Long amount, Long excess, String insuranceName) {
        this.id = id;
        this.carType = carType;
        this.carNumber = carNumber;
        this.billDate = billDate;
        this.paymentDate = paymentDate;
        this.bill = bill;
        this.amount = amount;
        this.excess = excess;
        this.insuranceName = insuranceName;
    }
    @QueryProjection
    public CarInsuranceDto(Long id, Long carId, LocalDateTime paymentDate, String insuranceName) {
        this.id = id;
        this.carId = carId;
        this.paymentDate = paymentDate;
        this.insuranceName = insuranceName;
    }
}
