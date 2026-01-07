package oncall.domain;

import java.util.ArrayList;
import java.util.List;
import oncall.dto.OncallResult;

public class ScheduleProcessor {

    private final Schedules schedules;

    public ScheduleProcessor(Schedules schedules) {
        this.schedules = schedules;
    }

    public List<OncallResult> process(Month month, DayOfWeek startDayOfWeek) {
        List<Nickname> schedule = new ArrayList<>();
        List<OncallResult> oncallResults = new ArrayList<>();
        DayOfWeek dayOfWeek = startDayOfWeek;
        for (int day = 1; day <= month.getEndOfMonth(); day++) {
            Nickname candidate;
            if (isWeekend(dayOfWeek, month, day)) {
                candidate = schedules.getWeekendSchedule().pollFirst();
                if (isDuplicated(schedule, candidate)) {
                    Nickname next = schedules.getWeekendSchedule().pollFirst();
                    schedules.getWeekendSchedule().addLast(next);
                    schedules.getWeekendSchedule().addFirst(candidate);
                    schedule.add(next);
                } else {
                    schedules.getWeekendSchedule().addLast(candidate);
                    schedule.add(candidate);
                }
            } else {
                candidate = schedules.getWeekdaySchedule().pollFirst();
                if (isDuplicated(schedule, candidate)) {
                    Nickname next = schedules.getWeekdaySchedule().pollFirst();
                    schedules.getWeekdaySchedule().addLast(next);
                    schedules.getWeekdaySchedule().addFirst(candidate);
                    schedule.add(next);
                } else {
                    schedules.getWeekdaySchedule().addLast(candidate);
                    schedule.add(candidate);
                }
            }
            boolean isHoliday = isHoliday(dayOfWeek, month, day);
            oncallResults.add(new OncallResult(month.getValue(), day, dayOfWeek.name(), isHoliday, schedule.getLast().getValue()));
            dayOfWeek = getNextDayOfweek(dayOfWeek);
        }
        return oncallResults;
    }

    private boolean isDuplicated(List<Nickname> schedule, Nickname candidate) {
        return !schedule.isEmpty() && schedule.getLast().equals(candidate);
    }

    private DayOfWeek getNextDayOfweek(DayOfWeek dayOfWeek) {
        return dayOfWeek.getNext();
    }

    public boolean isWeekend(DayOfWeek dayOfWeek, Month month, int day) {
        return dayOfWeek.isWeekend() || Holiday.isHoliday(month, day);
    }

    public boolean isHoliday(DayOfWeek dayOfWeek, Month month, int day) {
        return !dayOfWeek.isWeekend() && Holiday.isHoliday(month, day);
    }
}
