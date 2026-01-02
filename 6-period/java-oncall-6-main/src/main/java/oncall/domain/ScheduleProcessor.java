package oncall.domain;

import java.util.ArrayList;
import java.util.List;

public class ScheduleProcessor {

    private final Date date;
    private final Crews weekdaySchedule;
    private final Crews weekendSchedule;

    public ScheduleProcessor(Date date, Crews weekdaySchedule, Crews weekendSchedule) {
        this.date = date;
        this.weekdaySchedule = weekdaySchedule;
        this.weekendSchedule = weekendSchedule;
    }

    //TODO: 리팩토링(메서드 길이 제한 오버)
    public List<ScheduleInfo> process() {
        List<ScheduleInfo> scheduleInfos = new ArrayList<>();

        for (int i = date.getDay(); i <= date.findEndDate(); i++) {
            if (isHoliday(date)) {
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
