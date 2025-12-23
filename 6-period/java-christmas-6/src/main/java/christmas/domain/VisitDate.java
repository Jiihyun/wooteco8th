package christmas.domain;

import christmas.exception.ExceptionMessage;

public class VisitDate {

    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;

    private final int value;

    public VisitDate(int value) {
        validateRange(value);
        this.value = value;
    }

    private void validateRange(int value) {
        if (isOutOfRange(value)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_VISIT_DATE.getMessage());
        }
    }

    private boolean isOutOfRange(Integer number) {
        return number < MIN_DATE || number > MAX_DATE;
    }
}
