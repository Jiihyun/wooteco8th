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

    //TODO: 리팩토링(메서드 길이 제한 오버)
    public List<ScheduleInfo> process() {
        List<ScheduleInfo> scheduleInfos = new ArrayList<>();

        for (int i = date.getDay(); i <= date.findEndDate(); i++) {
            if (isHoliday(date)) {
                Crews weekendSchedule = schedule.getWeekendSchedule();
                Nickname nickname = weekendSchedule.peekFirst();
                if (!scheduleInfos.isEmpty() && scheduleInfos.getLast().hasDuplicateSchedule(nickname)) {
                    weekendSchedule.changeWithNextCrew();
                }
                Nickname removedNickname = weekendSchedule.remove();
                weekendSchedule.addLast(removedNickname);
                scheduleInfos.add(new ScheduleInfo(Date.from(date), removedNickname));
                date.plusDate();
                continue;
            }
            Crews weekdaySchedule = schedule.getWeekdaySchedule();
            Nickname nickname = weekdaySchedule.peekFirst();
            if (!scheduleInfos.isEmpty() && scheduleInfos.getLast().hasDuplicateSchedule(nickname)) {
                weekdaySchedule.changeWithNextCrew();
            }
            Nickname removedNickname = weekdaySchedule.remove();
            weekdaySchedule.addLast(removedNickname);
            scheduleInfos.add(new ScheduleInfo(Date.from(date), removedNickname));
            date.plusDate();
        }
        return scheduleInfos;
    }

    private boolean isHoliday(Date date) {
        return date.isWeekend() || date.isHoliday();
    }
}
