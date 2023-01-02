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
        for(int i =0; i<100; i++){
            Car car = new Car();
            car.setCarNumber(i+"하"+i);
            car.setCarType("소나타" + i);
            car.setStatus(false);
            car.setComment("");
            car.setCreateDate(LocalDateTime.now().minusDays(3L));
            car.setReleaseDate(LocalDateTime.now().minusDays(1L));
            carRepository.save(car);
        }
        for(int i =0; i<100; i++){
            Insurance insurance = new Insurance();
            insurance.setName("삼성" + i);
            insuranceRepository.save(insurance);
        }

    }
}
