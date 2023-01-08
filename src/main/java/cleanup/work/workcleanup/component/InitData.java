package cleanup.work.workcleanup.component;

import cleanup.work.workcleanup.entity.Car;
import cleanup.work.workcleanup.entity.CarInsurance;
import cleanup.work.workcleanup.entity.Insurance;
import cleanup.work.workcleanup.repository.CarInsuranceRepository;
import cleanup.work.workcleanup.repository.CarRepository;
import cleanup.work.workcleanup.repository.InsuranceRepository;
import cleanup.work.workcleanup.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData {

    private final CarRepository carRepository;
    private final InsuranceRepository insuranceRepository;
    private final CarInsuranceRepository carInsuranceRepository;

    @PostConstruct
    public void init() {
        for(int i =0; i<100; i++){
            Insurance insurance = new Insurance();
            insurance.setName("삼성" + i);
            insuranceRepository.save(insurance);
        }

        for(int i =0; i<100; i++){
            Car car = new Car();
            car.setCarNumber(i+"하"+i);
            car.setCarType("소나타" + i);
            car.setComment("");
            car.setCreateDate(LocalDateTime.now().minusDays(3L));
            car.setReleaseDate(LocalDateTime.now().minusDays(1L));
            carRepository.save(car);
        }

        for (long i = 1; i < 100; i++) {
            Car car = carRepository.findById(i).orElseThrow(RuntimeException::new);
            Insurance insurance = insuranceRepository.findById(i).orElseThrow(RuntimeException::new);

            CarInsurance carInsurance = new CarInsurance();
            carInsurance.setInsurance(insurance);
            carInsurance.setCar(car);
            carInsurance.setAmount(100L);
            carInsurance.setBill(200L);
            carInsurance.setBillDate(LocalDateTime.now());
            carInsurance.setPaymentDate(LocalDateTime.now());
            carInsurance.setExcess(300L);
            carInsuranceRepository.save(carInsurance);
        }


    }
}
