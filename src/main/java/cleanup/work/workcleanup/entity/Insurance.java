package cleanup.work.workcleanup.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Insurance {

    @Id
    @GeneratedValue
    @Column(name = "insurance_id")
    private Long id;

    private String name;                //보험명



    @OneToMany(mappedBy = "insurance")
    private List<CarInsurance> carInsurances;


}
