package cleanup.work.workcleanup.component;

import cleanup.work.workcleanup.entity.Car;
import cleanup.work.workcleanup.entity.Insurance;
import cleanup.work.workcleanup.repository.CarRepository;
import cleanup.work.workcleanup.repository.InsuranceRepository;
import cleanup.work.workcleanup.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitData {

    private final CarRepository carRepository;
    private final InsuranceRepository insuranceRepository;

    @PostConstruct
    public void init() {
        Car car1 = new Car();
        car1.setCarNumber("31하 1313");
        car1.setCarType("K5");
        car1.setStatus(false);
        car1.setCreateDate(LocalDateTime.now().minusDays(3L));
        car1.setReleaseDate(LocalDateTime.now().minusDays(1L));
        carRepository.save(car1);

        Car car2 = new Car();
        car2.setCarNumber("51하 1521");
        car2.setCarType("그랜저");
        car2.setStatus(false);
        car2.setCreateDate(LocalDateTime.now().minusDays(6L));
        carRepository.save(car2);

        Insurance insurance1 = new Insurance();
        insurance1.setName("삼성");
        insuranceRepository.save(insurance1);

        Insurance insurance2 = new Insurance();
        insurance2.setName("DB");
        insuranceRepository.save(insurance2);
    }
}
