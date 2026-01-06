package attendance.domain;

import java.util.List;

public class CrewHistory {

    private final List<Attendance> attendances;
    private final Expulsion expulsion;

    public CrewHistory(List<Attendance> attendances, Expulsion expulsion) {
        this.attendances = attendances;
        this.expulsion = expulsion;
    }
}
