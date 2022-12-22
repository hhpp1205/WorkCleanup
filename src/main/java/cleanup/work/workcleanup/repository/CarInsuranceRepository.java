package cleanup.work.workcleanup.repository;

import cleanup.work.workcleanup.entity.CarInsurance;
import cleanup.work.workcleanup.repository.dto.CarInsuranceListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarInsuranceRepository extends JpaRepository<CarInsurance, Long> {

}
