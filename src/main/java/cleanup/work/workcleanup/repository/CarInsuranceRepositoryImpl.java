package cleanup.work.workcleanup.repository;

import cleanup.work.workcleanup.controller.form.CarInsuranceSearchCond;
import cleanup.work.workcleanup.controller.form.CarSearchCond;
import cleanup.work.workcleanup.entity.CarInsurance;
import cleanup.work.workcleanup.repository.custom.CarInsuranceRepositoryCustom;
import cleanup.work.workcleanup.repository.dto.CarInsuranceDto;
import cleanup.work.workcleanup.repository.dto.CarInsuranceMatching;
import cleanup.work.workcleanup.repository.dto.QCarInsuranceDto;
import cleanup.work.workcleanup.repository.dto.QCarInsuranceMatching;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static cleanup.work.workcleanup.entity.QCar.*;
import static cleanup.work.workcleanup.entity.QCarInsurance.*;
import static cleanup.work.workcleanup.entity.QInsurance.*;
import static org.springframework.util.StringUtils.*;

@RequiredArgsConstructor
public class CarInsuranceRepositoryImpl implements CarInsuranceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CarInsuranceDto> searchCarInsuranceDto(CarInsuranceSearchCond carInsuranCond) {
        return queryFactory
                .select(new QCarInsuranceDto(
                        carInsurance.id,
                        car.carType,
                        car.carNumber,
                        carInsurance.billDate,
                        carInsurance.paymentDate,
                        carInsurance.bill,
                        carInsurance.amount,
                        carInsurance.excess,
                        insurance.name.as("insuranceName")))
                .from(carInsurance)
                .leftJoin(carInsurance.car, car)
                .leftJoin(carInsurance.insurance, insurance)
                .where(carTypeLike(carInsuranCond.getCarType()),
                        carNumberLike(carInsuranCond.getCarNumber()),
                        insuranceIdEq(carInsuranCond.getInsuranceId()),
                        billDateBetween(carInsuranCond.getBillDateStart(), carInsuranCond.getBillDateEnd()),
                        paymentDateBetween(carInsuranCond.getPaymentDateStart(), carInsuranCond.getPaymentDateEnd()))
                .fetch();
    }

    @Override
    public List<CarInsurance> getCarInsuranceList(CarSearchCond carCond) {
        return queryFactory
                .select(carInsurance)
                .from(carInsurance)
                .leftJoin(carInsurance.insurance, insurance)
                .fetchJoin()
                .where(carTypeLike(carCond.getCarType()),
                        carNumberLike(carCond.getCarNumber()),
                        createDateBetween(carCond.getCreateDateStart(), carCond.getCreateDateEnd()),
                        releaseDateBetween(carCond.getReleaseDateStart(), carCond.getReleaseDateEnd())
                )
                .fetch();
    }

    @Override
    public List<CarInsuranceDto> findCarInsuranceDtoByCarIds(List<Long> carIds) {
        return queryFactory
                .select(new QCarInsuranceDto(car.id, carInsurance.paymentDate, insurance.name))
                .from(carInsurance)
                .join(carInsurance.car, car)
                .join(carInsurance.insurance, insurance)
                .where(carInsurance.car.id.in(carIds))
                .fetch();
    }

//    ================================== 검색 조건 ==================================
    private BooleanExpression insuranceIdEq(Long insuranceId) {
        return (insuranceId != null) ? insurance.id.eq(insuranceId) : null;
    }


    private BooleanExpression carTypeLike(String carType) {
        return hasText(carType) ? car.carType.contains(carType) : null;
    }

    private BooleanExpression carNumberLike(String carNumber) {
        return hasText(carNumber) ? car.carType.contains(carNumber) : null;
    }

    private BooleanExpression createDateBetween(LocalDate start, LocalDate end) {
        if (start != null && end != null) {
            return car.createDate.between(start.atStartOfDay(), end.atStartOfDay());
        } else if (start == null && end != null) {
            return createDateLoe(end);
        } else {
            return createDateGoe(start);
        }
    }

    private BooleanExpression releaseDateBetween(LocalDate start, LocalDate end) {
        if (start != null && end != null) {
            return car.releaseDate.between(start.atStartOfDay(), end.atStartOfDay());
        } else if (start == null && end != null) {
            return releaseDateLoe(end);
        } else {
            return releaseDateGoe(start);
        }
    }

    private BooleanExpression createDateGoe(LocalDate date) {
        return (date != null) ? car.createDate.goe(date.atStartOfDay()) : null;
    }

    private BooleanExpression createDateLoe(LocalDate date) {
        return (date != null) ? car.createDate.loe(date.atStartOfDay().minusDays(1L)) : null;
    }

    private BooleanExpression releaseDateGoe(LocalDate date) {
        return (date != null) ? car.releaseDate.goe(date.atStartOfDay()) : null;
    }

    private BooleanExpression releaseDateLoe(LocalDate date) {
        return (date != null) ? car.releaseDate.loe(date.atStartOfDay().minusDays(1L)) : null;
    }


    private BooleanExpression billDateBetween(LocalDate start, LocalDate end) {
        if (start != null && end != null) {
            return carInsurance.paymentDate.between(start.atStartOfDay(), end.atStartOfDay());
        } else if (start == null && end != null) {
            return billDateLoe(end);
        } else {
            return billDateGoe(start);
        }

    }

    private BooleanExpression paymentDateBetween(LocalDate start, LocalDate end) {
        if (start != null && end != null) {
            return carInsurance.billDate.between(start.atStartOfDay(), end.atStartOfDay());
        } else if (start == null && end != null) {
            return paymentDateLoe(end);
        } else {
            return paymentDateGoe(start);
        }
    }

    private BooleanExpression billDateGoe(LocalDate date) {
        return (date != null) ? carInsurance.billDate.goe(date.atStartOfDay()) : null;
    }

    private BooleanExpression billDateLoe(LocalDate date) {
        return (date != null) ? carInsurance.billDate.loe(date.atStartOfDay().minusDays(1L)) : null;
    }

    private BooleanExpression paymentDateGoe(LocalDate date) {
        return (date != null) ? carInsurance.paymentDate.goe(date.atStartOfDay()) : null;
    }

    private BooleanExpression paymentDateLoe(LocalDate date) {
        return (date != null) ? carInsurance.paymentDate.loe(date.atStartOfDay().minusDays(1L)) : null;
    }



}
