package cleanup.work.workcleanup.service;

import cleanup.work.workcleanup.controller.form.CarForm;
import cleanup.work.workcleanup.controller.form.CarSearchCond;
import cleanup.work.workcleanup.entity.Car;
import cleanup.work.workcleanup.repository.CarInsuranceRepository;
import cleanup.work.workcleanup.repository.CarRepository;
import cleanup.work.workcleanup.repository.dto.CarDto;
import cleanup.work.workcleanup.repository.dto.CarInsuranceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
        car.setTow(form.getTow());
    }

    public List<CarDto> getCarList(CarSearchCond cond, Pageable pageable) {

        List<CarDto> carDtos = carRepository.searchCarDto(cond, pageable);
        List<Long> carIds = getCarIds(carDtos);
        List<CarInsuranceDto> carInsuranceDtos = carInsuranceRepository.findCarInsuranceDtoByCarIds(carIds);

        matchingCarAndInsurance(carDtos, carInsuranceDtos);

        return carDtos;
    }

    public void matchingCarAndInsurance(List<CarDto> carDtos, List<CarInsuranceDto> carInsuranceDtos) {
        for (CarDto cd : carDtos) {
            for (CarInsuranceDto ci : carInsuranceDtos) {
                if (cd.getId().equals(ci.getCarId())) {
                    cd.getCarInsuranceDtos().add(ci);
                }
            }
        }
    }

    public List<Long> getCarIds(List<CarDto> carDtos) {
        List<Long> carIds = carDtos.stream()
                .map(c -> c.getId())
                .collect(Collectors.toList());
        return carIds;
    }

}
