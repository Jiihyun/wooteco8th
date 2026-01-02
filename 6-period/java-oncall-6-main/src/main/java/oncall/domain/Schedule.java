package oncall.domain;

import java.util.List;

public class Schedule {

    private final Crews weekdaySchedule;
    private final Crews weekendSchedule;

    public Schedule(List<String> weekdaySchedule, List<String> weekendSchedule) {
        this.weekdaySchedule = createCrews(weekdaySchedule);
        this.weekendSchedule = createCrews(weekendSchedule);
    }

    private Crews createCrews(List<String> schedule) {
        return new Crews(schedule.stream()
                .map(Nickname::new)
                .toList());
    }

    public Crews getWeekdaySchedule() {
        return weekdaySchedule;
    }

    public Crews getWeekendSchedule() {
        return weekendSchedule;
    }
}
