package cleanup.work.workcleanup.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    private String carType;            //차종
    private String phoneNumber;         //핸드폰번호
    private String carNumber;           //차량번호
    private LocalDateTime createDate;   //입고날짜
    private LocalDateTime releaseDate;  //출고날짜
    private Long vat;                   //부과세
    private String comment;             //적요
    private Boolean status;              //사장확인
    private Boolean tow;                 //견인 유무

}
