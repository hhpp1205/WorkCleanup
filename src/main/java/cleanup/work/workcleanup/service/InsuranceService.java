package cleanup.work.workcleanup.service;

import cleanup.work.workcleanup.entity.Insurance;
import cleanup.work.workcleanup.repository.InsuranceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;

    @Transactional
    public Long createInsurance(Insurance insurance) {
        validateDuplicateInsurance(insurance);
        insuranceRepository.save(insurance);
        return insurance.getId();
    }

    private void validateDuplicateInsurance(Insurance insurance) {
        List<Insurance> insurances = insuranceRepository.findByName(insurance.getName());
        if (!insurances.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 보험이름입니다");
        }
    }
}
