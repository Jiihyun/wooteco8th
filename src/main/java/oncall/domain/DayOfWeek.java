package oncall.domain;

import java.util.Arrays;
import oncall.exception.ExceptionMessage;

public enum DayOfWeek {

    일, 월, 화, 수, 목, 금, 토;

    public static DayOfWeek from(String input) {
        return Arrays.stream(DayOfWeek.values())
                .filter(element -> element.name().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.DAYOFWEEK_NOT_FOUND.getMessage()));
    }
}
