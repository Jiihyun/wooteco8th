package attendance.domain;

import attendance.exception.ExceptionMessage;
import attendance.util.FileReader;
import attendance.util.Parser;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AttendanceHistory {

    public static final String ATTENDANCE_INFO_DELIMITER = ",";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    private final List<Attendance> history;

    public AttendanceHistory() {
        this.history = init();
    }

    private List<Attendance> init() {
        List<String> attendances = FileReader.readAttendances();
        List<Attendance> history = new ArrayList<>();
        for (String attendance : attendances) {
            List<String> info = Parser.parseByDelimiter(attendance, ATTENDANCE_INFO_DELIMITER);
            String nickname = info.getFirst();
            LocalDateTime localDateTime = parseLocalDateTime(info);
            boolean isMonday = isMonday(localDateTime);
            history.add(new Attendance(nickname, localDateTime, AttendanceState.of(isMonday, localDateTime)));
        }
        return history;
    }

    private LocalDateTime parseLocalDateTime(List<String> info) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return LocalDateTime.parse(info.getLast(), formatter);
    }

    private boolean isMonday(LocalDateTime localDateTime) {
        return DayOfWeek.MONDAY == localDateTime.getDayOfWeek();
    }

    public boolean containsAttendance(String nickname, LocalDateTime localDateTime) {
        return history.stream()
                .anyMatch(attendance -> attendance.exists(nickname, localDateTime));
    }

    public Attendance findAttendance(String nickname, int dayOfMonth) {
        return history.stream()
                .filter(attendance -> attendance.hasSameNickname(nickname)
                        && attendance.hasSameDateTime(dayOfMonth))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.HISTORY_NOT_EXISTS.getMessage()));
    }

    public boolean containsNickname(String nickname) {
        return history.stream()
                .anyMatch(attendance -> attendance.hasSameNickname(nickname));
    }

    public void put(Attendance attendance) {
        history.add(attendance);
    }

    public void edit(Attendance attendance, LocalDateTime dateTime) {
        attendance.editDateTime(dateTime);
        attendance.editAttendanceState(AttendanceState.of(isMonday(dateTime), dateTime));
    }
}
