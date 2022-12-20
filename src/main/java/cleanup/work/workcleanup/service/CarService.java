package cleanup.work.workcleanup.service;

import cleanup.work.workcleanup.controller.form.CarForm;
import cleanup.work.workcleanup.entity.Car;
import cleanup.work.workcleanup.repository.CarRepository;
import cleanup.work.workcleanup.repository.InsuranceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;


    @Transactional
    public Long createCar(CarForm form) {
        Car car = form.toEntity();

        validateDuplicateCar(car);
        carRepository.save(car);
        return car.getId();
    }

    @Transactional
    public void updateCar(Long carId, CarForm form) {
        Optional<Car> findCar = carRepository.findById(carId);
        if (findCar.isPresent()) {
            Car car = findCar.get();
            updateFormToCar(form, car);
        }
    }



    private static void updateFormToCar(CarForm form, Car car) {
        car.setCarType(form.getCarType());
        car.setPhoneNumber(form.getPhoneNumber());
        car.setCarNumber(form.getCarNumber());
        car.setCreateDate(form.getCreateDate());
        car.setReleaseDate(form.getReleaseDate());
        car.setVat(form.getVat());
        car.setComment(form.getComment());
        car.setStatus(form.getStatus());
        car.setTow(form.getTow());
    }

    private void validateDuplicateCar(Car car) {
        List<Car> result = carRepository.findByCarNumber(car.getCarNumber());
        if (!result.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 차량번호입니다.");
        }
    }

}
