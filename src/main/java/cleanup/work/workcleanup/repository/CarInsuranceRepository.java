package cleanup.work.workcleanup.repository;

import cleanup.work.workcleanup.entity.CarInsurance;
import cleanup.work.workcleanup.repository.custom.CarInsuranceRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarInsuranceRepository extends JpaRepository<CarInsurance, Long>, CarInsuranceRepositoryCustom {

}
