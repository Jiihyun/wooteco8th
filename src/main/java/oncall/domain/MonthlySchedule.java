package oncall.domain;

import oncall.exception.ExceptionMessage;

public class MonthlySchedule {

    private final Schedule weekdaySchedule;
    private final Schedule weekendSchedule;

    public MonthlySchedule(Schedule weekdaySchedule, Schedule weekendSchedule) {
        validateSize(weekdaySchedule, weekendSchedule);
        this.weekdaySchedule = weekdaySchedule;
        this.weekendSchedule = weekendSchedule;
    }

    private void validateSize(Schedule weekday, Schedule weekend) {
        if (weekday.getSize() != weekend.getSize()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_SCHEDULE_SIZE.getMessage());
        }
    }
}
