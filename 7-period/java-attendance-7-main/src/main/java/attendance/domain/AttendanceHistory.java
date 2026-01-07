package attendance.domain;

import attendance.exception.ExceptionMessage;
import attendance.util.FileReader;
import attendance.util.Parser;
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

//    public void putNoShowOfNickname(String nickname, LocalDate date) {
//        for (int day = 1; day < date.getDayOfMonth(); day++) {
//            if (!hasAttendanceByDay(findAllByNicknameAndDay(nickname, date), day) && isWeekDay(day)) {
//                histories.add(new Attendance(
//                        nickname,
//                        LocalDateTime.of(
//                                LocalDate.of(2024, 12, day),
//                                LocalTime.MIN),
//                        AttendanceState.결석));
//            }
//        }
//    }
//
//    private boolean hasAttendanceByDay(List<Attendance> attendances, int day) {
//        return attendances.stream()
//                .anyMatch(attendance -> attendance.hasSameDay(day));
//    }
//
//    private boolean isWeekDay(int day) {
//        DayOfWeek dayOfWeek = LocalDate.of(2024, 12, day).getDayOfWeek();
//        return dayOfWeek != DayOfWeek.SATURDAY
//                && dayOfWeek != DayOfWeek.SUNDAY
//                && day != AttendanceProcessor.CHRISTMAS_DAY;
//    }
//
//    public List<Attendance> findAllByNicknameAndDay(String nickname, LocalDate dateOfToday) {
//        return new ArrayList<>(histories.stream()
//                .filter(attendance -> attendance.hasSameNickname(nickname))
//                .filter(attendance -> attendance.getDayOfMonth() < dateOfToday.getDayOfMonth())
//                .toList());
//    }
//
//    public Set<String> findAllNames() {
//        return histories.stream()
//                .map(Attendance::getNickname)
//                .collect(Collectors.toSet());
//    }
}
