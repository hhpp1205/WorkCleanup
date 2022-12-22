package cleanup.work.workcleanup.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarInsuranceListDto {

    private Long id;

    private String carType;             //차량종류
    private String carNumber;             //차량번호
    private LocalDateTime createDate;    //청구일자
    private LocalDateTime paymentDate;  //입금일자
    private Long amount;                //지급액
    private Long excess;                //면책금
}
