package cleanup.work.workcleanup.converter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LocalDateAndLocalDateTimeConverter {

    public static LocalDateTime localDateToLocalDateTime(LocalDate localDate) {
        return (localDate != null) ? localDate.atStartOfDay() : null;
    }

    public static LocalDate localDateTimeDateToLocalDate(LocalDateTime localDateTime) {
        return (localDateTime != null) ? localDateTime.toLocalDate() : null;
    }
}
