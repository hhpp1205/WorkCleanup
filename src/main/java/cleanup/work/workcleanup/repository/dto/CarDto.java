package cleanup.work.workcleanup.repository.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    private Long id;
    private String carType;            //차종
    private String phoneNumber;         //핸드폰번호
    private String carNumber;           //차량번호
    private LocalDateTime createDate;   //입고날짜
    private LocalDateTime releaseDate;  //출고날짜
    private Long vat;                   //부과세
    private String comment;             //적요
    private Long tow;                 //견인 유무

    private List<CarInsuranceDto> carInsuranceDtos = new ArrayList<>();


    public CarDto(Long id, String carType, String carNumber) {
        this.id = id;
        this.carType = carType;
        this.carNumber = carNumber;
    }

    @QueryProjection
    public CarDto(Long id, String carType, String phoneNumber, String carNumber, LocalDateTime createDate, LocalDateTime releaseDate, Long vat, String comment, Long tow) {
        this.id = id;
        this.carType = carType;
        this.phoneNumber = phoneNumber;
        this.carNumber = carNumber;
        this.createDate = createDate;
        this.releaseDate = releaseDate;
        this.vat = vat;
        this.comment = comment;
        this.tow = tow;
    }

    @QueryProjection
    public CarDto(Long id, String carType) {
        this.id = id;
        this.carType = carType;
    }
}
