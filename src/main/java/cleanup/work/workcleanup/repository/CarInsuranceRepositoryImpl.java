package cleanup.work.workcleanup.repository;

import cleanup.work.workcleanup.controller.form.CarInsuranceSearchCond;
import cleanup.work.workcleanup.repository.custom.CarInsuranceRepositoryCustom;
import cleanup.work.workcleanup.repository.dto.CarInsuranceDto;
import cleanup.work.workcleanup.repository.dto.QCarInsuranceDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static cleanup.work.workcleanup.entity.QCar.*;
import static cleanup.work.workcleanup.entity.QCarInsurance.*;
import static org.springframework.util.StringUtils.*;

@RequiredArgsConstructor
public class CarInsuranceRepositoryImpl implements CarInsuranceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CarInsuranceDto> searchCarInsuranceDto(CarInsuranceSearchCond cond) {
        return queryFactory
                .select(new QCarInsuranceDto(
                        carInsurance.id,
                        car.carType,
                        car.carNumber,
                        carInsurance.createDate,
                        carInsurance.paymentDate,
                        carInsurance.amount,
                        carInsurance.excess))
                .from(carInsurance)
                .leftJoin(carInsurance.car, car)
                .where(carTypeLike(cond.getCarType()),
                        carNumberLike(cond.getCarNumber()))
                .fetch();
    }

    private BooleanExpression carTypeLike(String carType) {
        return hasText(carType) ? car.carType.like(carType) : null;
    }

    private BooleanExpression carNumberLike(String carNumber) {
        return hasText(carNumber) ? car.carType.like(carNumber) : null;
    }

}
