package attendance.domain;

import attendance.exception.ExceptionMessage;
import attendance.util.FileReader;
import attendance.util.Parser;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceHistory {

    public static final String ATTENDANCE_INFO_DELIMITER = ",";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    private final Map<String, CrewHistory> histories;

    public AttendanceHistory() {
        this.histories = init();
    }

    private Map<String, CrewHistory> init() {
        Map<String, CrewHistory> histories = new HashMap<>();
        List<String> attendances = FileReader.readAttendances();
        for (String attendance : attendances) {
            List<String> info = Parser.parseByDelimiter(attendance, ATTENDANCE_INFO_DELIMITER);
            String nickname = info.getFirst();
            LocalDateTime dateTime = parseLocalDateTime(info);
            CrewHistory crewHistory = histories.computeIfAbsent(nickname, key -> new CrewHistory());
            crewHistory.put(dateTime.getDayOfMonth(), new Attendance(dateTime, AttendanceState.from(dateTime)));
        }
        return histories;
    }

    private LocalDateTime parseLocalDateTime(List<String> info) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return LocalDateTime.parse(info.getLast(), formatter);
    }

    public boolean notContainsNickname(String nickname) {
        return !histories.containsKey(nickname);
    }

    public boolean containsHistory(String nickname, int dayOfMonth) {
        if (!histories.containsKey(nickname)) {
            return false;
        }
        CrewHistory crewHistory = histories.get(nickname);
        return crewHistory.containsHistoryOfDay(dayOfMonth);
    }

    public void put(String nickname, LocalDateTime dateTime, AttendanceState attendanceState) {
        CrewHistory crewHistory = histories.computeIfAbsent(nickname, key -> new CrewHistory());
        crewHistory.put(dateTime.getDayOfMonth(),
                new Attendance(dateTime, attendanceState));
    }

    public Attendance findAttendanceByDayOfMonth(String nickname, int dayOfMonth) {
        if (!containsHistory(nickname, dayOfMonth)) {
            throw new IllegalArgumentException(ExceptionMessage.HISTORY_NOT_EXISTS.getMessage());
        }
        CrewHistory crewHistory = histories.get(nickname);
        return crewHistory.findAttendanceByDayOfMonth(dayOfMonth);
    }

    public void edit(Attendance attendance, LocalDateTime dateTime) {
        attendance.editDateTime(dateTime);
        attendance.editAttendanceState(AttendanceState.from(dateTime));
    }

    public List<Attendance> findCrewHistoryByNickname(String nickname, LocalDate dateOfToday) {
        if (notContainsNickname(nickname)) {
            throw new IllegalArgumentException(ExceptionMessage.NICKNAME_NOT_FOUND.getMessage());
        }
        CrewHistory crewHistory = histories.get(nickname);
        crewHistory.putAbsence(dateOfToday);
        return crewHistory.getAllAttendance();
    }

//    public Set<String> findAllNames() {
//        return histories.stream()
//                .map(Attendance::getNickname)
//                .collect(Collectors.toSet());
//    }
}
