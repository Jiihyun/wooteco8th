package oncall.domain;

import java.util.Arrays;
import oncall.exception.ExceptionMessage;

public enum DayOfWeek {

    MON("월"),
    TUE("화"),
    WED("수"),
    THU("목"),
    FRI("금"),
    SAT("토"),
    SUN("일"),
    ;

    private final String description;

    DayOfWeek(String description) {
        this.description = description;
    }

    public static DayOfWeek from(String input) {
        return Arrays.stream(DayOfWeek.values())
                .filter(element -> element.description.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.DAYOFWEEK_NOT_EXISTS.getMessage()));
    }

    public boolean isWeekend() {
        return this == SAT || this == SUN;
    }

    public DayOfWeek getNextDayOfWeek() {
        //TODO: 동적처리
        if (this == MON) {
            return TUE;
        }
        if (this == TUE) {
            return WED;
        }
        if (this == WED) {
            return THU;
        }
        if (this == THU) {
            return FRI;
        }
        if (this == FRI) {
            return SAT;
        }
        if (this == SAT) {
            return SUN;
        }
        return MON;
    }

    public String getDescription() {
        return description;
    }
}
