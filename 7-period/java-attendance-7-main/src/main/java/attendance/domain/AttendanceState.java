package attendance.domain;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public enum AttendanceState {

    결석,
    지각,
    출석;

    private static final LocalTime MONDAY_START_TIME = LocalTime.of(13, 0);
    private static final LocalTime NORMAL_START_TIME = LocalTime.of(10, 0);

    private static final int LATE_THRESHOLD = 5;
    private static final int ABSENT_THRESHOLD = 30;

    public static AttendanceState from(LocalDateTime dateTime) {
        LocalTime startTime = getStartTime(dateTime);
        if (calculateOverTime(startTime, dateTime) > ABSENT_THRESHOLD) {
            return AttendanceState.결석;
        }
        if (calculateOverTime(startTime, dateTime) > LATE_THRESHOLD) {
            return AttendanceState.지각;
        }
        return AttendanceState.출석;
    }

    private static LocalTime getStartTime(LocalDateTime dateTime) {
        if (isMonday(dateTime)) {
            return MONDAY_START_TIME;
        }
        return NORMAL_START_TIME;
    }

    private static boolean isMonday(LocalDateTime dateTime) {
        return DayOfWeek.MONDAY == dateTime.getDayOfWeek();
    }

    private static long calculateOverTime(LocalTime startTime, LocalDateTime dateTime) {
        return ChronoUnit.MINUTES.between(startTime, dateTime.toLocalTime());
    }
}
