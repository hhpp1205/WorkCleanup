package cleanup.work.workcleanup.service;

import cleanup.work.workcleanup.controller.form.CarInsuranceForm;
import cleanup.work.workcleanup.entity.Car;
import cleanup.work.workcleanup.entity.CarInsurance;
import cleanup.work.workcleanup.entity.Insurance;
import cleanup.work.workcleanup.repository.CarInsuranceRepository;
import cleanup.work.workcleanup.repository.CarRepository;
import cleanup.work.workcleanup.repository.InsuranceRepository;
import cleanup.work.workcleanup.repository.dto.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


/**
 * 1. 보험 수리비 명세표 추가 차량, 보험 불러오기
 * 1-1.차량 목록 불러오기 차종 , 차량번호
 * 1-2.보험 목록 불러오기 보험이름
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CarInsuranceService {

    private final CarRepository carRepository;
    private final InsuranceRepository insuranceRepository;
    private final CarInsuranceRepository carInsuranceRepository;

    public List<CarDto> getCarList() {
        List<Car> carList = carRepository.findCarByStatusFalse();

        return carList.stream()
                .map(c -> new CarDto(c.getId(), c.getCarType(), c.getCarNumber()))
                .collect(toList());
    }

    public void createCarInsurance(Long carId, Long insuranceId, CarInsuranceForm form) {
        Car car = carRepository.findById(carId).orElseThrow(NoSuchElementException::new);
        Insurance insurance = insuranceRepository.findById(insuranceId).orElseThrow(NoSuchElementException::new);

        carInsuranceRepository.save(CarInsurance.create(car, insurance, form));
    }
}
