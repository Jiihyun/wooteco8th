package attendance.domain;

import attendance.exception.ExceptionMessage;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class AttendanceProcessor {

    private final int CHRISTMAS_DAY = 25;
    private final AttendanceHistory attendanceHistory;

    public AttendanceProcessor(AttendanceHistory attendanceHistory) {
        this.attendanceHistory = attendanceHistory;
    }

    public Nickname findNickname(String nickname) {
        return attendanceHistory.findNickname(nickname);
    }

    public void checkAttendance(Nickname nickname, LocalDateTime dateTime) {
        if (attendanceHistory.containsAttendance(nickname, dateTime)) {
            throw new IllegalArgumentException(ExceptionMessage.HISTORY_ALREADY_EXISTS.getMessage());
        }
        if (!isWeekDay(dateTime)) {
            throw new IllegalArgumentException(ExceptionMessage.CANNOT_ATTENDANCE.getFormattedMessage(
                    dateTime.getDayOfMonth(),
                    dateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN)));
        }
        if (isNotCampusRunningTime(dateTime)) {
            throw new IllegalArgumentException(ExceptionMessage.CAMPUS_CLOSED_TIME.getMessage());
        }
    }

    private boolean isWeekDay(LocalDateTime time) {
        DayOfWeek dayOfWeek = time.getDayOfWeek();
        return dayOfWeek != DayOfWeek.SATURDAY
                && dayOfWeek != DayOfWeek.SUNDAY
                && time.getDayOfMonth() != CHRISTMAS_DAY;
    }

    private boolean isNotCampusRunningTime(LocalDateTime time) {
        LocalTime now = LocalTime.of(time.getHour(), time.getMinute());
        return now.isBefore(LocalTime.of(8, 0))
                || now.isAfter(LocalTime.of(23, 0));
    }
}
