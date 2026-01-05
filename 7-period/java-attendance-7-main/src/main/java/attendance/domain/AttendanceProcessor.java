package attendance.domain;

import attendance.dto.EditResult;
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

    public void checkAttendance(String nickname, LocalDateTime dateTime) {
        validateNickname(nickname);
        //TODO 출석 지각 판단
        if (attendanceHistory.containsAttendance(nickname, dateTime)) {
            throw new IllegalArgumentException(ExceptionMessage.HISTORY_ALREADY_EXISTS.getMessage());
        }
        validateWeekDay(dateTime);
        validateRunningTime(dateTime);
    }

    private void validateNickname(String nickname) {
        if (!attendanceHistory.containsNickname(nickname)) {
            throw new IllegalArgumentException(ExceptionMessage.NICKNAME_NOT_FOUND.getMessage());
        }
    }

    private void validateWeekDay(LocalDateTime dateTime) {
        if (!isWeekDay(dateTime)) {
            throw new IllegalArgumentException(ExceptionMessage.CANNOT_ATTENDANCE.getFormattedMessage(
                    dateTime.getDayOfMonth(),
                    dateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN)));
        }
    }

    private boolean isWeekDay(LocalDateTime time) {
        DayOfWeek dayOfWeek = time.getDayOfWeek();
        return dayOfWeek != DayOfWeek.SATURDAY
                && dayOfWeek != DayOfWeek.SUNDAY
                && time.getDayOfMonth() != CHRISTMAS_DAY;
    }

    private void validateRunningTime(LocalDateTime dateTime) {
        if (isNotCampusRunningTime(dateTime)) {
            throw new IllegalArgumentException(ExceptionMessage.CAMPUS_CLOSED_TIME.getMessage());
        }
    }

    private boolean isNotCampusRunningTime(LocalDateTime time) {
        LocalTime now = LocalTime.of(time.getHour(), time.getMinute());
        return now.isBefore(LocalTime.of(8, 0))
                || now.isAfter(LocalTime.of(23, 0));
    }

    public EditResult editAttendance(String nickname, LocalDateTime afterDateTime) {
        validateNickname(nickname);
        validateWeekDay(afterDateTime);
        validateRunningTime(afterDateTime);
        Attendance attendance = attendanceHistory.findAttendance(nickname, afterDateTime.getDayOfMonth());
        LocalDateTime beforeDateTime = attendance.getDateTime();
        attendanceHistory.edit(attendance, afterDateTime);
        return new EditResult(beforeDateTime, afterDateTime);
    }
}
