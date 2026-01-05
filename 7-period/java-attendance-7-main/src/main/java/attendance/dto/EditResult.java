package attendance.dto;

import attendance.domain.AttendanceState;
import java.time.LocalDateTime;

public record EditResult(
        LocalDateTime before,
        AttendanceState beforeAttendanceState,
        LocalDateTime after,
        AttendanceState afterAttendanceState
) {
}
