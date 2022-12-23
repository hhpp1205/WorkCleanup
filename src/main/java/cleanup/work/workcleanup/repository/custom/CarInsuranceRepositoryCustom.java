package cleanup.work.workcleanup.repository.custom;

import cleanup.work.workcleanup.controller.form.CarInsuranceSearchCond;
import cleanup.work.workcleanup.repository.dto.CarInsuranceDto;

import java.util.List;

public interface CarInsuranceRepositoryCustom {
    List<CarInsuranceDto> searchCarInsuranceDto(CarInsuranceSearchCond cond);
}
