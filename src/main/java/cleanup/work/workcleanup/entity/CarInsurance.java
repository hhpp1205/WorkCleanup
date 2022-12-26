package cleanup.work.workcleanup.entity;

import cleanup.work.workcleanup.controller.form.CarInsuranceForm;
import cleanup.work.workcleanup.converter.LocalDateAndLocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static cleanup.work.workcleanup.converter.LocalDateAndLocalDateTimeConverter.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarInsurance {

    @Id
    @GeneratedValue
    @Column(name = "car_insurance_id")
    private Long id;

    private LocalDateTime billDate;    //청구일자
    private LocalDateTime paymentDate;  //입금일자
    private Long bill;                  //청구액
    private Long amount;                //지급액
    private Long excess;                //면책금

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;

    public static CarInsurance create(Car car, Insurance insurance, CarInsuranceForm form) {
        CarInsurance carInsurance = new CarInsurance();
        carInsurance.setBillDate(localDateToLocalDateTime(form.getBillDate()));
        carInsurance.setPaymentDate(localDateToLocalDateTime(form.getPaymentDate()));
        carInsurance.setBill(form.getBill());
        carInsurance.setAmount(form.getAmount());
        carInsurance.setExcess(form.getExcess());
        carInsurance.setCar(car);
        carInsurance.setInsurance(insurance);

        return carInsurance;
    }

    public CarInsuranceForm toCarInsuranceForm() {
        return CarInsuranceForm.builder()
                .id(id)
                .billDate(localDateTimeDateToLocalDate(billDate))
                .paymentDate(localDateTimeDateToLocalDate(paymentDate))
                .bill(bill)
                .amount(amount)
                .excess(excess)
                .carId(car.getId())
                .insuranceId(insurance.getId())
                .build();
    }
}
