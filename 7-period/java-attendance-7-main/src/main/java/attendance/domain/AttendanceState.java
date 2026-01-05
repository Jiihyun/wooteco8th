package attendance.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public enum AttendanceState {

    결석,
    지각,
    출석;

    private static final LocalTime MONDAY_START_TIME = LocalTime.of(13, 0);
    private static final LocalTime NORMAL_START_TIME = LocalTime.of(10, 0);

    //TODO 등교하지 않아 출석 기록이 없는 날은 결석 처리
    public static AttendanceState of(boolean isMonday, LocalDateTime dateTime) {
        if (isMonday) {
            if (calculateOverTime(MONDAY_START_TIME, dateTime) > 30) {
                return AttendanceState.결석;
            }
            if (calculateOverTime(MONDAY_START_TIME, dateTime) > 5) {
                return AttendanceState.지각;
            }
            return AttendanceState.출석;
        }
        if (calculateOverTime(NORMAL_START_TIME, dateTime) > 30) {
            return AttendanceState.결석;
        }
        if (calculateOverTime(NORMAL_START_TIME, dateTime) > 5) {
            return AttendanceState.지각;
        }
        return AttendanceState.출석;
    }

    private static long calculateOverTime(LocalTime startTime, LocalDateTime dateTime) {
        return ChronoUnit.MINUTES.between(startTime, dateTime.toLocalTime()) % 60;
    }
}
