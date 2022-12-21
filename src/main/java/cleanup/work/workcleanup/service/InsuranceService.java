package cleanup.work.workcleanup.service;

import cleanup.work.workcleanup.controller.form.InsuranceForm;
import cleanup.work.workcleanup.entity.Insurance;
import cleanup.work.workcleanup.repository.InsuranceRepository;
import cleanup.work.workcleanup.repository.dto.InsuranceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;

    @Transactional
    public Long createInsurance(InsuranceForm form) {
        Insurance insurance = new Insurance(form.getName());

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

    public List<InsuranceDto> getInsuranceList() {
        List<Insurance> insuranceList = insuranceRepository.getInsuranceList();

        return insuranceList.stream()
                .map(i -> new InsuranceDto(i.getId(), i.getName()))
                .collect(toList());
    }
}
