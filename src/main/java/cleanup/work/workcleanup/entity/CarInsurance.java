package cleanup.work.workcleanup.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarInsurance {

    @Id
    @GeneratedValue
    @Column(name = "car_insurance_id")
    private Long id;

    private LocalDateTime createDate;    //청구일자
    private LocalDateTime paymentDate;  //입금일자
    private Long amount;                //지급액
    private Long excess;                //면책금

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;
}
