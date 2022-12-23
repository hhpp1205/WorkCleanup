package cleanup.work.workcleanup.controller.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CarSearchCond {

    private String carType;            //차종
    private String carNumber;           //차량번호
    private String phoneNumber;         //핸드폰번호

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDateStart;   //입고날짜
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDateEnd;   //입고날짜

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDateStart;  //출고날짜
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDateEnd;  //출고날짜


}
