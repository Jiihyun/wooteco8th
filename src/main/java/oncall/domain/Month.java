package oncall.domain;

import java.util.Arrays;
import oncall.exception.ExceptionMessage;

public enum Month {

    JAN(1, 31),
    FEB(2, 28),
    MAR(3, 31),
    APL(4, 30),
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
    private final int endOfMonth;

    Month(int value, int endOfMonth) {
        this.value = value;
        this.endOfMonth = endOfMonth;
    }

    public static Month from(int value) {
        return Arrays.stream(Month.values())
                .filter(element -> element.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.MONTH_NOT_FOUND.getMessage()));
    }
}
