package cleanup.work.workcleanup.service;

import cleanup.work.workcleanup.repository.CarInsuranceRepository;
import cleanup.work.workcleanup.repository.CarRepository;
import cleanup.work.workcleanup.repository.InsuranceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarInsuranceService {

    private final CarInsuranceRepository carInsuranceRepository;
    private final CarRepository carRepository;
    private final InsuranceRepository insuranceRepository;

//    public Long createCarInsurance(Long carId, Long insuranceId,) {
//
//    }
}
