package cleanup.work.workcleanup.service;

import cleanup.work.workcleanup.controller.form.CarInsuranceForm;
import cleanup.work.workcleanup.controller.form.CarInsuranceSearchCond;
import cleanup.work.workcleanup.entity.Car;
import cleanup.work.workcleanup.entity.CarInsurance;
import cleanup.work.workcleanup.entity.Insurance;
import cleanup.work.workcleanup.repository.CarInsuranceRepository;
import cleanup.work.workcleanup.repository.CarRepository;
import cleanup.work.workcleanup.repository.InsuranceRepository;
import cleanup.work.workcleanup.repository.dto.CarDto;
import cleanup.work.workcleanup.repository.dto.CarInsuranceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static cleanup.work.workcleanup.converter.LocalDateAndLocalDateTimeConverter.*;
import static java.util.stream.Collectors.*;


/**
 * 1. 보험 수리비 명세표 추가 차량, 보험 불러오기
 * 1-1.차량 목록 불러오기 차종 , 차량번호
 * 1-2.보험 목록 불러오기 보험이름
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarInsuranceService {

    private final CarRepository carRepository;
    private final InsuranceRepository insuranceRepository;
    private final CarInsuranceRepository carInsuranceRepository;

    public List<CarDto> getCarList() {
        List<Car> carList = carRepository.findCarByReleaseDateIsNull();

        return carList.stream()
                .map(c -> new CarDto(c.getId(), c.getCarType(), c.getCarNumber()))
                .collect(toList());
    }

    public void createCarInsurance(Long carId, Long insuranceId, CarInsuranceForm form) {
        Car car = carRepository.findById(carId).orElseThrow(NoSuchElementException::new);
        Insurance insurance = insuranceRepository.findById(insuranceId).orElseThrow(NoSuchElementException::new);

        carInsuranceRepository.save(CarInsurance.create(car, insurance, form));
    }

    public List<CarInsuranceDto> getCarInsuranceList(CarInsuranceSearchCond cond) {
        return carInsuranceRepository.searchCarInsuranceDto(cond);
    }

    public CarInsuranceForm findByCarInsuranceId(Long carInsuranceId) {
        Optional<CarInsurance> findCarInsurance = carInsuranceRepository.findById(carInsuranceId);

        if(findCarInsurance.isEmpty())
            throw new NoSuchElementException("해당 ID로 CarInsurance를 찾을 수 없습니다.");

        CarInsurance carInsurance = findCarInsurance.get();
        return carInsurance.toCarInsuranceForm();

    }

    @Transactional
    public void updateCarInsurance(Long carInsuranceId, CarInsuranceForm carInsuranceForm) {
        CarInsurance carInsurance = carInsuranceRepository.findByIdFetch(carInsuranceId).orElseThrow(NoSuchElementException::new);
        updateFormToCarInsurance(carInsurance, carInsuranceForm);
    }


    private void updateFormToCarInsurance(CarInsurance carInsurance, CarInsuranceForm form) {
        Car findCar = carRepository.findById(form.getCarId()).orElseThrow(NoSuchElementException::new);
        Insurance findInsurance = insuranceRepository.findById(form.getInsuranceId()).orElseThrow(NoSuchElementException::new);

        carInsurance.setBillDate(localDateToLocalDateTime(form.getBillDate()));
        carInsurance.setPaymentDate(localDateToLocalDateTime(form.getPaymentDate()));
        carInsurance.setBill(form.getBill());
        carInsurance.setAmount(form.getAmount());
        carInsurance.setExcess(form.getExcess());
//        carInsurance.setCar(findCar);
        carInsurance.setInsurance(findInsurance);
    }
}
