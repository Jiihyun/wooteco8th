package attendance.domain;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public enum AttendanceState {

    결석(30),
    지각(5),
    출석(0);

    private static final LocalTime MONDAY_START_TIME = LocalTime.of(13, 0);
    private static final LocalTime NORMAL_START_TIME = LocalTime.of(10, 0);

    private final int threshord;

    AttendanceState(int threshord) {
        this.threshord = threshord;
    }

    public static AttendanceState from(LocalDateTime dateTime) {
        LocalTime startTime = getStartTime(dateTime);
        if (calculateOverTime(startTime, dateTime) > 결석.threshord) {
            return AttendanceState.결석;
        }
        if (calculateOverTime(startTime, dateTime) > 지각.threshord) {
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
