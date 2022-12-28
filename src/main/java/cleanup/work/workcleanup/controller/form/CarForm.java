package cleanup.work.workcleanup.controller.form;

import cleanup.work.workcleanup.converter.LocalDateAndLocalDateTimeConverter;
import cleanup.work.workcleanup.entity.Car;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static cleanup.work.workcleanup.converter.LocalDateAndLocalDateTimeConverter.*;

@Data
@NoArgsConstructor
public class CarForm {

    private Long id;
    private String carType;            //차종
    private String phoneNumber;         //핸드폰번호
    private String carNumber;           //차량번호
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;   //입고날짜
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;  //출고날짜
    private Long vat;                   //부과세
    private String comment;             //적요
    private Boolean status;              //사장확인
    private Long tow;                 //견인 유무

    @Builder
    public CarForm(Long id, String carType, String phoneNumber, String carNumber, LocalDate createDate, LocalDate releaseDate, Long vat, String comment, Boolean status, Long tow) {
        this.id = id;
        this.carType = carType;
        this.phoneNumber = phoneNumber;
        this.carNumber = carNumber;
        this.createDate = createDate;
        this.releaseDate = releaseDate;
        this.vat = vat;
        this.comment = comment;
        this.status = status;
        this.tow = tow;
    }

    public Car toEntity() {
        return Car.builder()
                .carType(carType)
                .phoneNumber(phoneNumber)
                .carNumber(carNumber)
                .vat(vat)
                .comment(comment)
                .status(status)
                .tow(tow)
                .createDate(localDateToLocalDateTime(createDate))
                .releaseDate(localDateToLocalDateTime(releaseDate))
                .build();
    }


}
