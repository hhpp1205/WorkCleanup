package cleanup.work.workcleanup.repository;

import cleanup.work.workcleanup.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

    List<Insurance> findByName(String insuranceName);

    List<Insurance> findByIdIn(List<Long> ids);

    @Query("select distinct i from Insurance i ")
    List<Insurance> getInsuranceList();
}
