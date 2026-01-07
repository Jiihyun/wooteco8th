package attendance.domain;

import attendance.dto.AttendanceResult;
import attendance.exception.ExceptionMessage;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
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

    public void validateWeekDay(LocalDate date) {
        if (isWeekend(date)) {
            throw new IllegalArgumentException(ExceptionMessage.CANNOT_ATTENDANCE.getFormattedMessage(
                    date.getDayOfMonth(),
                    date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN)));
        }
    }

    private boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY
                || dayOfWeek == DayOfWeek.SUNDAY
                || date.getDayOfMonth() == CHRISTMAS_DAY;
    }

    public void validateNickname(String nickname) {
        if (attendanceHistory.notContainsNickname(nickname)) {
            throw new IllegalArgumentException(ExceptionMessage.NICKNAME_NOT_FOUND.getMessage());
        }
    }

    public void validateRunningTime(LocalTime time) {
        if (isNotCampusRunningTime(time)) {
            throw new IllegalArgumentException(ExceptionMessage.CAMPUS_CLOSED_TIME.getMessage());
        }
    }

    private boolean isNotCampusRunningTime(LocalTime time) {
        return time.isBefore(LocalTime.of(8, 0))
                || time.isAfter(LocalTime.of(23, 0));
    }

    public AttendanceResult checkAttendance(String nickname, LocalTime time) {
        LocalDateTime dateTime = LocalDateTime.of(dateOfToday, time);
        if (attendanceHistory.containsHistory(nickname, dateTime)) {
            throw new IllegalArgumentException(ExceptionMessage.HISTORY_ALREADY_EXISTS.getMessage());
        }
        AttendanceState attendanceState = AttendanceState.from(dateTime);
        attendanceHistory.put(nickname, dateTime, attendanceState);
        return new AttendanceResult(dateTime, attendanceState);
    }

//    public EditResult editAttendance(String nickname, LocalDateTime afterDateTime) {
//        validateRunningTime(afterDateTime.toLocalTime());
//        Attendance attendance = attendanceHistory.findAttendance(nickname, afterDateTime.getDayOfMonth());
//        LocalDateTime beforeDateTime = attendance.getDateTime();
//        AttendanceState beforeAttendanceState = attendance.getAttendanceState();
//        attendanceHistory.edit(attendance, afterDateTime);
//        return new EditResult(beforeDateTime, beforeAttendanceState, afterDateTime, attendance.getAttendanceState());
//    }
//
//    public ShowResult showAttendance(String nickname) {
//        attendanceHistory.putNoShowOfNickname(nickname, dateOfToday);
//        List<Attendance> attendances = attendanceHistory.findAllByNicknameAndDay(nickname, dateOfToday);
//        int lateCount = countAttendanceState(attendances, AttendanceState.지각);
//        int noshowCount = countAttendanceState(attendances, AttendanceState.결석);
//        noshowCount += lateCount / 3;
//
//        return new ShowResult(
//                attendances,
//                countAttendanceState(attendances, AttendanceState.출석),
//                countAttendanceState(attendances, AttendanceState.지각),
//                countAttendanceState(attendances, AttendanceState.결석),
//                Expulsion.from(noshowCount)
//        );
//    }
//
//    private int countAttendanceState(List<Attendance> attendances, AttendanceState state) {
//        return (int) attendances.stream()
//                .filter(attendance -> attendance.getAttendanceState() == state)
//                .count();
//    }
//
//    public List<ShowResult> findExpulsionCrews() {
//        List<ShowResult> results = new ArrayList<>();
//        for (String nickname : attendanceHistory.findAllNames()) {
//            ShowResult showResult = showAttendance(nickname);
//            if (showResult.expulsion() != Expulsion.NONE) {
//                results.add(showResult);
//            }
//        }
//        return results;
//    }
}
