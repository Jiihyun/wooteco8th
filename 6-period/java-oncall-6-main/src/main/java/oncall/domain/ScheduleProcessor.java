package oncall.domain;

import java.util.ArrayList;
import java.util.List;

public class ScheduleProcessor {

    private final Date date;
    private final Schedule schedule;

    public ScheduleProcessor(Date date, Schedule schedule) {
        this.date = date;
        this.schedule = schedule;
    }

    public List<ScheduleInfo> process() {
        List<ScheduleInfo> scheduleInfos = new ArrayList<>();
        boolean isHolidayChanged = false;
        boolean isWeekdayChanged = false;
        while (date.underEndDate()) {
            if (isHoliday(date)) {
                isHolidayChanged = putSchedule(schedule.getWeekendSchedule(), scheduleInfos, isHolidayChanged);
                continue;
            }
            isWeekdayChanged = putSchedule(schedule.getWeekdaySchedule(), scheduleInfos, isWeekdayChanged);
        }
        return scheduleInfos;
    }

    private boolean isHoliday(Date date) {
        return date.isWeekend() || date.isHoliday();
    }

    private boolean putSchedule(Crews crews, List<ScheduleInfo> infos, boolean isChanged) {
        boolean changed = assign(crews, infos, isChanged);
        date.plusDate();
        return changed;
    }

    private boolean assign(Crews crews, List<ScheduleInfo> infos, boolean isChanged) {
        if (isChanged) {
            crews.rollbackTurn(date, infos);
            return false;
        }
        if (scheduleDuplicated(infos, crews.peekFirst())) {
            crews.changeTurn(date, infos);
            return true;
        }
        crews.assignNormally(date, infos);
        return false;
    }

    private boolean scheduleDuplicated(List<ScheduleInfo> scheduleInfos, Nickname nickname) {
        return !scheduleInfos.isEmpty()
                && scheduleInfos.getLast().hasDuplicateSchedule(nickname);
    }
}
