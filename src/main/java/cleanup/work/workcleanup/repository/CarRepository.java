package cleanup.work.workcleanup.repository;

import cleanup.work.workcleanup.entity.Car;
import cleanup.work.workcleanup.repository.custom.CarRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long>, CarRepositoryCustom {

    List<Car> findByCarNumber(String carNumber);
    List<Car> findAllByOrderByCreateDateDesc();

    List<Car> findCarByStatusFalse();


}
