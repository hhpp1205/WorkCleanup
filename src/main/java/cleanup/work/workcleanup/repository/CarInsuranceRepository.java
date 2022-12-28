package cleanup.work.workcleanup.repository;

import cleanup.work.workcleanup.entity.CarInsurance;
import cleanup.work.workcleanup.repository.custom.CarInsuranceRepositoryCustom;
import cleanup.work.workcleanup.repository.dto.CarInsuranceMatching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CarInsuranceRepository extends JpaRepository<CarInsurance, Long>, CarInsuranceRepositoryCustom {


    @Query("select ci from CarInsurance ci join fetch ci.car join fetch ci.insurance where ci.id = :carInsuranceId")
    Optional<CarInsurance> findByIdFetch(@Param("carInsuranceId") Long carInsuranceId);

}
