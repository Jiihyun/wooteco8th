package attendance.domain;

import static attendance.domain.AttendanceProcessor.CHRISTMAS_DAY;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
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

    public void putAbsence(LocalDate today) {
        for (int day = 1; day < today.getDayOfMonth(); day++) {
            LocalDate date = LocalDate.of(2024, 12, day);
            if (!containsHistoryOfDay(day) && isWeekDay(date)) {
                crewHistory.put(day,
                        new Attendance(
                                LocalDateTime.of(date, LocalTime.MIN),
                                AttendanceState.결석));
            }
        }
    }

    private boolean isWeekDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.SATURDAY
                && dayOfWeek != DayOfWeek.SUNDAY
                && date.getDayOfMonth() != CHRISTMAS_DAY;
    }

    public List<Attendance> getAllAttendance() {
        return crewHistory.values().stream().toList();
    }
}
