package cleanup.work.workcleanup.repository;

import cleanup.work.workcleanup.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

    List<Insurance> findByName(String insuranceName);

    List<Insurance> findByIdIn(List<Long> ids);
}
