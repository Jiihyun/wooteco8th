package oncall.domain;

import java.util.Arrays;
import oncall.exception.ExceptionMessage;

public enum DayOfWeek {

    일(0),
    월(1),
    화(2),
    수(3),
    목(4),
    금(5),
    토(6);

    private final int index;

    DayOfWeek(int index) {
        this.index = index;
    }

    public static DayOfWeek from(String input) {
        return Arrays.stream(DayOfWeek.values())
                .filter(element -> element.name().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.DAYOFWEEK_NOT_FOUND.getMessage()));
    }

    public boolean isWeekend() {
        return this == 토 || this == 일;
    }

    public DayOfWeek getNext() {
        int nextIndex = (this.index + 1) % DayOfWeek.values().length;
        return Arrays.stream(DayOfWeek.values())
                .filter(element -> element.index == nextIndex)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.DAYOFWEEK_NOT_FOUND.getMessage()));
    }
}
