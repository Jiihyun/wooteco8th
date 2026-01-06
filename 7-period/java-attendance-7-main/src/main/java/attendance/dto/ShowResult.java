package attendance.dto;

import attendance.domain.Attendance;
import attendance.domain.Expulsion;
import java.util.List;

public record ShowResult(
        List<Attendance> attendances,
        int attendance,
        int lateness,
        int noShow,
        Expulsion expulsion) {
}
