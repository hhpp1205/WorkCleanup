package cleanup.work.workcleanup.repository.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CarInsuranceMatching {

    private Long carId;
    private String insuranceName;

    @QueryProjection
    public CarInsuranceMatching(Long carId, String insuranceName) {
        this.carId = carId;
        this.insuranceName = insuranceName;
    }
}
