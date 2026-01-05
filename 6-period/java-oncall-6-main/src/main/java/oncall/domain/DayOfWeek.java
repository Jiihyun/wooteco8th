package oncall.domain;

import java.util.Arrays;
import java.util.List;
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

    private static final List<DayOfWeek> processOrder = makeOrder();

    private static List<DayOfWeek> makeOrder() {
        return Arrays.stream(DayOfWeek.values())
                .toList();
    }

    public static DayOfWeek from(String input) {
        return Arrays.stream(DayOfWeek.values())
                .filter(element -> element.description.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.DAYOFWEEK_NOT_EXISTS.getMessage()));
    }

    private final String description;

    DayOfWeek(String description) {
        this.description = description;
    }

    public boolean isWeekend() {
        return this == SAT || this == SUN;
    }

    public DayOfWeek getNextDayOfWeek() {
        int index = processOrder.indexOf(this);
        if (index == processOrder.size() - 1) {
            return processOrder.getFirst();
        }
        return processOrder.get(index + 1);
    }

    public String getDescription() {
        return description;
    }
}
