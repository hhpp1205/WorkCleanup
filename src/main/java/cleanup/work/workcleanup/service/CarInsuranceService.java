package cleanup.work.workcleanup.service;

import cleanup.work.workcleanup.repository.CarInsuranceRepository;
import cleanup.work.workcleanup.repository.CarRepository;
import cleanup.work.workcleanup.repository.InsuranceRepository;
import cleanup.work.workcleanup.repository.dto.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 1. 보험 수리비 명세표 추가 차량, 보험 불러오기
 * 1-1.차량 목록 불러오기 차종 , 차량번호
 * 1-2.보험 목록 불러오기 보험이름
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CarInsuranceService {

    private final CarInsuranceRepository carInsuranceRepository;
    private final CarRepository carRepository;
    private final InsuranceRepository insuranceRepository;

    public List<CarDto> getCarlist() {
        // TODO: 2022-12-20 차량 목록 불러오기
        return null;
    }

//    public Long createCarInsurance(Long carId, Long insuranceId,) {
//
//    }
}
