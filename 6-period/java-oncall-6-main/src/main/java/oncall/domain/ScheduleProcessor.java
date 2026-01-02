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
        while (date.underEndDate()) {
            if (isHoliday(date)) {
                putSchedule(schedule.getWeekendSchedule(), scheduleInfos);
                continue;
            }
            putSchedule(schedule.getWeekdaySchedule(), scheduleInfos);
        }
        return scheduleInfos;
    }

    private boolean isHoliday(Date date) {
        return date.isWeekend() || date.isHoliday();
    }

    private void putSchedule(Crews schedule, List<ScheduleInfo> scheduleInfos) {
        Nickname nickname = schedule.peekFirst();
        changeScheduleIfDuplicated(scheduleInfos, nickname, schedule);
        Nickname removedNickname = schedule.remove();
        schedule.addLast(removedNickname);
        scheduleInfos.add(new ScheduleInfo(Date.from(date), removedNickname));
        date.plusDate();
    }

    private void changeScheduleIfDuplicated(List<ScheduleInfo> scheduleInfos, Nickname nickname, Crews weekendSchedule) {
        if (scheduleDuplicated(scheduleInfos, nickname)) {
            weekendSchedule.changeWithNextCrew();
        }
    }

    private boolean scheduleDuplicated(List<ScheduleInfo> scheduleInfos, Nickname nickname) {
        return !scheduleInfos.isEmpty()
                && scheduleInfos.getLast().hasDuplicateSchedule(nickname);
    }
}
