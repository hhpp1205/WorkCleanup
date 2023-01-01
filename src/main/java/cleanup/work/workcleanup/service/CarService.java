package cleanup.work.workcleanup.service;

import cleanup.work.workcleanup.controller.form.CarForm;
import cleanup.work.workcleanup.controller.form.CarSearchCond;
import cleanup.work.workcleanup.entity.Car;
import cleanup.work.workcleanup.repository.CarInsuranceRepository;
import cleanup.work.workcleanup.repository.CarRepository;
import cleanup.work.workcleanup.repository.dto.CarDto;
import cleanup.work.workcleanup.repository.dto.CarInsuranceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static cleanup.work.workcleanup.converter.LocalDateAndLocalDateTimeConverter.localDateToLocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarInsuranceRepository carInsuranceRepository;


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

    public List<CarDto> getCarList(CarSearchCond cond) {

        List<CarDto> carDtos = carRepository.searchCarDto(cond);

        List<Long> carIds = carDtos.stream()
                .map(c -> c.getId())
                .collect(Collectors.toList());

        List<CarInsuranceDto> carInsuranceDtos = carInsuranceRepository.findInsuranceNameByCarIds(carIds);

        carDtos.stream().forEach(
                carDto -> carDto.setCarInsuranceDtos(matchingCarInsuranceDto(carInsuranceDtos, carDto))
        );

        return carDtos;
    }

    private static List<CarInsuranceDto> matchingCarInsuranceDto(List<CarInsuranceDto> carInsuranceDtos, CarDto carDto) {
        return carInsuranceDtos.stream()
                .filter(ci -> carDto.getId() == ci.getCarId())
                .map(ci -> new CarInsuranceDto(ci.getCarId(), ci.getPaymentDate(), ci.getInsuranceName()))
                .toList();
    }

}
