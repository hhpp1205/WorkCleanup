package cleanup.work.workcleanup.repository;

import cleanup.work.workcleanup.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByCarNumber(String carNumber);
    List<Car> findAllByOrderByCreateDateDesc();

}
