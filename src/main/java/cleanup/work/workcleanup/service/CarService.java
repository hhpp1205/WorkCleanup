package cleanup.work.workcleanup.service;

import cleanup.work.workcleanup.controller.form.CarForm;
import cleanup.work.workcleanup.entity.Car;
import cleanup.work.workcleanup.repository.CarRepository;
import cleanup.work.workcleanup.repository.InsuranceRepository;
import cleanup.work.workcleanup.repository.dto.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static cleanup.work.workcleanup.controller.form.CarForm.*;
import static cleanup.work.workcleanup.converter.LocalDateAndLocalDateTimeConverter.localDateToLocalDateTime;

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

    public CarForm findCarById(Long carId) {
        Optional<Car> findCar = carRepository.findById(carId);
        if(findCar.isEmpty())
            throw new NoSuchElementException("해당 ID로 Car를 찾을 수 없습니다.");
        Car car = findCar.get();
        return car.toCarForm();
    }



    private void updateFormToCar(CarForm form, Car car) {
        car.setCarType(form.getCarType());
        car.setPhoneNumber(form.getPhoneNumber());
        car.setCarNumber(form.getCarNumber());
        car.setCreateDate(localDateToLocalDateTime(form.getCreateDate()));
        car.setReleaseDate(localDateToLocalDateTime(form.getReleaseDate()));
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
