package cleanup.work.workcleanup.entity;

import cleanup.work.workcleanup.controller.form.CarForm;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cleanup.work.workcleanup.converter.LocalDateAndLocalDateTimeConverter.localDateTimeDateToLocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;

    private String carType;            //차종
    private String phoneNumber;         //핸드폰번호
    private String carNumber;           //차량번호
    private LocalDateTime createDate;   //입고날짜
    private LocalDateTime releaseDate;  //출고날짜
    private Long vat;                   //부과세
    private String comment;             //적요
    private Long tow;                 //견인 유무

    @OneToMany(mappedBy = "car")
    private List<CarInsurance> carInsurances = new ArrayList<>();  // 보험 1개일수도 2개일수도 있음

    @Builder
    public Car(String carType, String phoneNumber, String carNumber, LocalDateTime createDate, LocalDateTime releaseDate, Long vat, String comment, Long tow) {
        this.carType = carType;
        this.phoneNumber = phoneNumber;
        this.carNumber = carNumber;
        this.createDate = createDate;
        this.releaseDate = releaseDate;
        this.vat = vat;
        this.comment = comment;
        this.tow = tow;
        this.carInsurances = carInsurances;
    }

    public CarForm toCarForm() {
               return CarForm.builder()
                .carType(carType)
                .phoneNumber(phoneNumber)
                .carNumber(carNumber)
                .createDate(localDateTimeDateToLocalDate(createDate))
                .releaseDate(localDateTimeDateToLocalDate(releaseDate))
                .vat(vat)
                .comment(comment)
                .tow(tow)
                .build();
    }
}
