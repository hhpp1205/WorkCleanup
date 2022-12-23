package cleanup.work.workcleanup.repository.custom;

import cleanup.work.workcleanup.controller.form.CarSearchCond;
import cleanup.work.workcleanup.repository.dto.CarDto;

import java.util.List;

public interface CarRepositoryCustom {

    List<CarDto> searchCarDto(CarSearchCond cond);
}
