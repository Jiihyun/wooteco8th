package attendance.domain;

import java.util.HashMap;
import java.util.Map;

public class CrewHistory {

    private final Map<Integer, Attendance> crewHistory;

    public CrewHistory() {
        this.crewHistory = new HashMap<>();
    }

    public void put(int day, Attendance attendance) {
        crewHistory.put(day, attendance);
    }

    public boolean containsHistoryOfDay(int day) {
        return crewHistory.containsKey(day);
    }

    public Attendance findAttendanceByDayOfMonth(int dayOfMonth) {
        return crewHistory.get(dayOfMonth);
    }
}
