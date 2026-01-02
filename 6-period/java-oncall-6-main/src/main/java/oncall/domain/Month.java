package oncall.domain;

import java.util.Arrays;
import oncall.exception.ExceptionMessage;

public enum Month {

    JAN(1, 31),
    FEB(2, 28),
    MAR(3, 31),
    APR(4, 30),
    MAY(5, 31),
    JUN(6, 30),
    JUL(7, 31),
    AUG(8, 31),
    SEP(9, 30),
    OCT(10, 31),
    NOV(11, 30),
    DEC(12, 31),
    ;

    private final int value;
    private final int endDate;

    Month(int value, int endDate) {
        this.value = value;
        this.endDate = endDate;
    }

    public static Month from(int value) {
        return Arrays.stream(Month.values())
                .filter(element -> element.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.MONTH_NOT_EXISTS.getMessage()));
    }

    public int getValue() {
        return value;
    }

    public int getEndDate() {
        return endDate;
    }
}
