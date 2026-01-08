package attendance.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceStateHistory {

    private static final int LATE_LIMIT = 3;

    private final Map<AttendanceState, Integer> history;

    public AttendanceStateHistory() {
        this.history = new HashMap<>();
    }

    public int calculateAttendanceStateCount(List<Attendance> attendances, AttendanceState state) {
        return 0;
    }

    public int countAttendanceState(List<Attendance> attendances, AttendanceState state) {
        return (int) attendances.stream()
                .filter(attendance -> attendance.getAttendanceState() == state)
                .count();
    }
}
