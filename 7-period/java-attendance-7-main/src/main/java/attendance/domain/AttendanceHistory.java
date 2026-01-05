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

    private final Map<Nickname, LocalDateTime> history;

    public AttendanceHistory() {
        this.history = init();
    }

    private Map<Nickname, LocalDateTime> init() {
        List<String> attendances = FileReader.readAttendances();

        Map<Nickname, LocalDateTime> history = new HashMap<>();
        for (String attendance : attendances) {
            List<String> info = Parser.parseByDelimiter(attendance, ATTENDANCE_INFO_DELIMITER);
            String nickname = info.getFirst();
            LocalDateTime localDateTime = parseLocalDateTime(info);
            history.put(new Nickname(nickname), localDateTime);
        }
        return history;
    }

    private LocalDateTime parseLocalDateTime(List<String> info) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return LocalDateTime.parse(info.getLast(), formatter);
    }

    public Nickname findNickname(String otherNickname) {
        return history.keySet().stream()
                .filter(nickname -> nickname.hasSameValue(otherNickname))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.NICKNAME_NOT_FOUND.getMessage()));
    }

    public boolean containsAttendance(Nickname nickname, LocalDateTime localDateTime) {
        return history.containsKey(findNickname(nickname.getValue()))
                && history.containsValue(localDateTime);
    }

    private boolean containsNickname(String otherValue) {
        return history.keySet().stream()
                .anyMatch(nickname -> nickname.getValue().equals(otherValue));
    }
}
