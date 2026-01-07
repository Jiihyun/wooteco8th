package attendance.domain;

import attendance.dto.AttendanceResult;
import attendance.dto.EditResult;
import attendance.dto.ShowResult;
import attendance.exception.ExceptionMessage;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AttendanceProcessor {

    public static final int CHRISTMAS_DAY = 25;

    private final LocalDate dateOfToday;
    private final AttendanceHistory attendanceHistory;

    public AttendanceProcessor(LocalDate dateOfToday, AttendanceHistory attendanceHistory) {
        validateWeekDay(dateOfToday);
        this.dateOfToday = dateOfToday;
        this.attendanceHistory = attendanceHistory;
    }

    public AttendanceResult checkAttendance(String nickname, LocalTime time) {
        LocalDateTime dateTime = LocalDateTime.of(dateOfToday, time);
        if (attendanceHistory.containsAttendance(nickname, dateTime)) {
            throw new IllegalArgumentException(ExceptionMessage.HISTORY_ALREADY_EXISTS.getMessage());
        }
        validateRunningTime(dateTime);
        Attendance attendance = new Attendance(nickname, dateTime, AttendanceState.of(isMonday(dateTime), dateTime));
        attendanceHistory.put(attendance);
        return new AttendanceResult(dateTime, attendance.getAttendanceState());
    }

    public void validateNickname(String nickname) {
        if (!attendanceHistory.containsNickname(nickname)) {
            throw new IllegalArgumentException(ExceptionMessage.NICKNAME_NOT_FOUND.getMessage());
        }
    }

    public void validateWeekDay(LocalDate date) {
        if (!isWeekDay(date)) {
            throw new IllegalArgumentException(ExceptionMessage.CANNOT_ATTENDANCE.getFormattedMessage(
                    date.getDayOfMonth(),
                    date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN)));
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

    public ShowResult showAttendance(String nickname) {
        attendanceHistory.putNoShowOfNickname(nickname, dateOfToday);
        List<Attendance> attendances = attendanceHistory.findAllByNicknameAndDay(nickname, dateOfToday);
        int lateCount = countAttendanceState(attendances, AttendanceState.지각);
        int noshowCount = countAttendanceState(attendances, AttendanceState.결석);
        noshowCount += lateCount / 3;

        return new ShowResult(
                attendances,
                countAttendanceState(attendances, AttendanceState.출석),
                countAttendanceState(attendances, AttendanceState.지각),
                countAttendanceState(attendances, AttendanceState.결석),
                Expulsion.from(noshowCount)
        );
    }

    private int countAttendanceState(List<Attendance> attendances, AttendanceState state) {
        return (int) attendances.stream()
                .filter(attendance -> attendance.getAttendanceState() == state)
                .count();
    }

    public List<ShowResult> findExpulsionCrews() {
        List<ShowResult> results = new ArrayList<>();
        for (String nickname : attendanceHistory.findAllNames()) {
            ShowResult showResult = showAttendance(nickname);
            if (showResult.expulsion() != Expulsion.NONE) {
                results.add(showResult);
            }
        }
        return results;
    }
}
