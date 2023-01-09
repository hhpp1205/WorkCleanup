package cleanup.work.workcleanup.repository.custom;

import cleanup.work.workcleanup.controller.form.CarSearchCond;
import cleanup.work.workcleanup.controller.form.ExcelForm;
import cleanup.work.workcleanup.repository.dto.CarDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarRepositoryCustom {

    List<CarDto> searchCarDto(CarSearchCond cond, Pageable pageable);
    List<CarDto> excelSearch(ExcelForm excelForm);

    Long getCount(CarSearchCond cond);

}
