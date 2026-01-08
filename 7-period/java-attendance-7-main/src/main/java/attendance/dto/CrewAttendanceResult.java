package attendance.dto;

import attendance.domain.Attendance;
import attendance.domain.Expulsion;
import java.util.List;

public record CrewAttendanceResult(
        List<Attendance> attendances,
        int attendanceCount,
        int lateCount,
        int absentCount,
        Expulsion expulsion) {
}
