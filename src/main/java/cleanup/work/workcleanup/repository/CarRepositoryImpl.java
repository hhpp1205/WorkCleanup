package cleanup.work.workcleanup.repository;

import cleanup.work.workcleanup.controller.form.CarSearchCond;
import cleanup.work.workcleanup.controller.form.ExcelForm;
import cleanup.work.workcleanup.repository.custom.CarRepositoryCustom;
import cleanup.work.workcleanup.repository.dto.CarDto;
import cleanup.work.workcleanup.repository.dto.QCarDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import static cleanup.work.workcleanup.entity.QCar.*;
import static org.springframework.util.StringUtils.*;

@RequiredArgsConstructor
public class CarRepositoryImpl implements CarRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public List<CarDto> searchCarDto(CarSearchCond cond, Pageable pageable) {
        return queryFactory
                .select(
                        new QCarDto(
                                car.id,
                                car.carType,
                                car.phoneNumber,
                                car.carNumber,
                                car.createDate,
                                car.releaseDate,
                                car.vat,
                                car.comment,
                                car.tow))
                .from(car)
                .where(
                        carTypeLike(cond.getCarType()),
                        carNumberLike(cond.getCarNumber()),
                        phoneNumberLike(cond.getPhoneNumber()),
                        createDateBetween(cond.getCreateDateStart(), cond.getCreateDateEnd()),
                        releaseDateBetween(cond.getReleaseDateStart(), cond.getReleaseDateEnd())
                )
                .orderBy(car.createDate.desc(), car.releaseDate.desc(), car.carType.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<CarDto> excelSearch(ExcelForm excelForm) {
        return queryFactory
                .select(
                        new QCarDto(
                                car.id,
                                car.carType,
                                car.phoneNumber,
                                car.carNumber,
                                car.createDate,
                                car.releaseDate,
                                car.vat,
                                car.comment,
                                car.tow))
                .from(car)
                .where(
                        createDateBetween(excelForm.getStartDate(), excelForm.getEndDate())
                )
                .orderBy(car.createDate.desc())
                .fetch();
    }

    @Override
    public Long getCount(CarSearchCond cond) {
        return queryFactory
                .select(car.count())
                .from(car)
                .where(
                        carTypeLike(cond.getCarType()),
                        carNumberLike(cond.getCarNumber()),
                        phoneNumberLike(cond.getPhoneNumber()),
                        createDateBetween(cond.getCreateDateStart(), cond.getCreateDateEnd()),
                        releaseDateBetween(cond.getReleaseDateStart(), cond.getReleaseDateEnd())
                )
                .fetchOne();
    }


    private BooleanExpression carTypeLike(String carType) {
        return hasText(carType) ? car.carType.contains(carType) : null;
    }

    private BooleanExpression carNumberLike(String carNumber) {
        return hasText(carNumber) ? car.carNumber.contains(carNumber) : null;
    }

    private BooleanExpression phoneNumberLike(String phoneNumber) {
        return hasText(phoneNumber) ? car.phoneNumber.contains(phoneNumber) : null;
    }

    private BooleanExpression createDateBetween(LocalDate start, LocalDate end) {
        if (start != null && end != null) {
            return car.createDate.between(start.atStartOfDay(), end.atStartOfDay());
        } else if (start == null && end != null) {
            return dateLoe(end);
        } else {
            return dateGoe(start);
        }
    }

    private BooleanExpression releaseDateBetween(LocalDate start, LocalDate end) {
        if (start != null && end != null) {
            return car.releaseDate.between(start.atStartOfDay(), end.atStartOfDay());
        } else if (start == null && end != null) {
            return dateLoe(end);
        } else {
            return dateGoe(start);
        }
    }

    private BooleanExpression dateGoe(LocalDate createDate) {
        return (createDate != null) ? car.createDate.goe(createDate.atStartOfDay()) : null;
    }

    private BooleanExpression dateLoe(LocalDate ceateDate) {
        return (ceateDate != null) ? car.createDate.loe(ceateDate.atStartOfDay().minusDays(1L)) : null;
    }


}
