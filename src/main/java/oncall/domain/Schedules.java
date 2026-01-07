package oncall.domain;

import oncall.exception.ExceptionMessage;

public class Schedules {

    private final Schedule weekdaySchedule;
    private final Schedule weekendSchedule;

    public Schedules(Schedule weekdaySchedule, Schedule weekendSchedule) {
        validateSize(weekdaySchedule, weekendSchedule);
        this.weekdaySchedule = weekdaySchedule;
        this.weekendSchedule = weekendSchedule;
    }

    private void validateSize(Schedule weekday, Schedule weekend) {
        if (weekday.getSize() != weekend.getSize()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_SCHEDULE_SIZE.getMessage());
        }
    }


    public Schedule getWeekdaySchedule() {
        return weekdaySchedule;
    }

    public Schedule getWeekendSchedule() {
        return weekendSchedule;
    }
}
