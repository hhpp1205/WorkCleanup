package cleanup.work.workcleanup.repository.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class CarInsuranceMatching {

    private Long carId;
    private String insuranceName;
    private LocalDateTime paymentDate;

    @QueryProjection
    public CarInsuranceMatching(Long carId, String insuranceName, LocalDateTime paymentDate) {
        this.carId = carId;
        this.insuranceName = insuranceName;
        this.paymentDate = paymentDate;
    }
}
