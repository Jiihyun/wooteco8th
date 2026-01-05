package attendance.domain;

import attendance.dto.EditResult;
import attendance.exception.ExceptionMessage;
import java.time.DayOfWeek;
import java.time.LocalDate;
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

    public AttendanceState checkAttendance(String nickname, LocalDateTime dateTime) {
        if (attendanceHistory.containsAttendance(nickname, dateTime)) {
            throw new IllegalArgumentException(ExceptionMessage.HISTORY_ALREADY_EXISTS.getMessage());
        }
        validateRunningTime(dateTime);
        Attendance attendance = new Attendance(nickname, dateTime, AttendanceState.of(isMonday(dateTime), dateTime));
        attendanceHistory.put(attendance);
        return attendance.getAttendanceState();
    }

    public void validateNickname(String nickname) {
        if (!attendanceHistory.containsNickname(nickname)) {
            throw new IllegalArgumentException(ExceptionMessage.NICKNAME_NOT_FOUND.getMessage());
        }
    }

    public void validateWeekDay(LocalDate localDate) {
        if (!isWeekDay(localDate)) {
            throw new IllegalArgumentException(ExceptionMessage.CANNOT_ATTENDANCE.getFormattedMessage(
                    localDate.getDayOfMonth(),
                    localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN)));
        }
    }

    private boolean isWeekDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.SATURDAY
                && dayOfWeek != DayOfWeek.SUNDAY
                && date.getDayOfMonth() != CHRISTMAS_DAY;
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

    private boolean isMonday(LocalDateTime localDateTime) {
        return DayOfWeek.MONDAY == localDateTime.getDayOfWeek();
    }

    public EditResult editAttendance(String nickname, LocalDateTime afterDateTime) {
        validateRunningTime(afterDateTime);
        Attendance attendance = attendanceHistory.findAttendance(nickname, afterDateTime.getDayOfMonth());
        LocalDateTime beforeDateTime = attendance.getDateTime();
        AttendanceState beforeAttendanceState = attendance.getAttendanceState();
        attendanceHistory.edit(attendance, afterDateTime);
        return new EditResult(beforeDateTime, beforeAttendanceState, afterDateTime, attendance.getAttendanceState());
    }
}
