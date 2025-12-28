package christmas.domain;

import christmas.exception.ExceptionMessage;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitDate {

    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int CHRISTMAS_DAY = 25;

    private final LocalDate value;

    public VisitDate(int day) {
        validateRange(day);
        this.value = LocalDate.of(YEAR, MONTH, day);
    }

    private void validateRange(int day) {
        if (isOutOfRange(day)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_VISIT_DATE.getMessage());
        }
    }

    private boolean isOutOfRange(Integer number) {
        return number < MIN_DATE || number > MAX_DATE;
    }

    public boolean isWeekday() {
        return this.value.getDayOfWeek() != DayOfWeek.FRIDAY
                && this.value.getDayOfWeek() != DayOfWeek.SATURDAY;
    }

    public boolean isWeekend() {
        return !isWeekday();
    }

    public boolean isSpecialDay() {
        return this.value.getDayOfWeek() == DayOfWeek.SUNDAY
                || this.value.getDayOfMonth() == CHRISTMAS_DAY;

    }

    public boolean isRightRange(int minRange, int maxRange) {
        LocalDate minDate = LocalDate.of(YEAR, MONTH, minRange);
        LocalDate maxDate = LocalDate.of(YEAR, MONTH, maxRange);
        return this.value.isEqual(minDate) || this.value.isAfter(minDate)
                && this.value.isEqual(maxDate) || this.value.isBefore(maxDate);
    }

    public int getDayOfMonth() {
        return value.getDayOfMonth();
    }
}
