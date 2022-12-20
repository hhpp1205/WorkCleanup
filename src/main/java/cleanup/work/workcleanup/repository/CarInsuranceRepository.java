package cleanup.work.workcleanup.repository;

import cleanup.work.workcleanup.entity.CarInsurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarInsuranceRepository extends JpaRepository<CarInsurance, Long> {
}
