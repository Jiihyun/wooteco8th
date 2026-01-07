package attendance.dto;

import attendance.domain.AttendanceState;
import java.time.LocalDateTime;

public record AttendanceResult(
        LocalDateTime dateTime,
        AttendanceState attendanceState
) {
}
