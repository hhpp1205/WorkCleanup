package cleanup.work.workcleanup.repository.custom;

import cleanup.work.workcleanup.controller.form.CarInsuranceSearchCond;
import cleanup.work.workcleanup.controller.form.CarSearchCond;
import cleanup.work.workcleanup.entity.CarInsurance;
import cleanup.work.workcleanup.repository.dto.CarInsuranceDto;

import java.util.List;

public interface CarInsuranceRepositoryCustom {
    List<CarInsuranceDto> searchCarInsuranceDto(CarInsuranceSearchCond cond);

    List<CarInsurance> getCarInsuranceList(CarSearchCond cond);
}
