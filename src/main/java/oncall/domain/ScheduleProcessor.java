package oncall.domain;

import java.util.ArrayList;
import java.util.List;
import oncall.dto.OncallResult;

public class ScheduleProcessor {

    private final Schedules schedules;
    private final List<Nickname> monthlySchedule;

    public ScheduleProcessor(Schedules schedules) {
        this.schedules = schedules;
        this.monthlySchedule = new ArrayList<>();
    }

    public List<OncallResult> process(Month month, DayOfWeek startDayOfWeek) {
        List<OncallResult> oncallResults = new ArrayList<>();
        boolean weekdayChanged = false;
        boolean weekendChanged = false;
        put(month, startDayOfWeek, weekendChanged, oncallResults, weekdayChanged);
        return oncallResults;
    }

    private void put(Month month, DayOfWeek dayOfWeek, boolean weekendChanged, List<OncallResult> oncallResults, boolean weekdayChanged) {
        for (int day = 1; day <= month.getEndOfMonth(); day++) {
            Nickname candidate;
            if (isWeekend(dayOfWeek, month, day)) {
                candidate = schedules.getWeekendSchedule().pollFirst();
                weekendChanged = putSchedule(candidate, schedules.getWeekendSchedule(), weekendChanged);
                addResult(month, dayOfWeek, day, oncallResults);
                dayOfWeek = getNextDayOfweek(dayOfWeek);
                continue;
            }
            candidate = schedules.getWeekdaySchedule().pollFirst();
            weekdayChanged = putSchedule(candidate, schedules.getWeekdaySchedule(), weekdayChanged);
            addResult(month, dayOfWeek, day, oncallResults);
            dayOfWeek = getNextDayOfweek(dayOfWeek);
        }
    }

    private boolean putSchedule(Nickname candidate, Schedule schedule, boolean isChanged) {
        if (isDuplicated(monthlySchedule, candidate)) {
            return processDuplicated(schedule, candidate);
        }
        if (isChanged) {
            return processWhenChanged(candidate, schedule);
        }
        return processDefaultWay(schedule, candidate);
    }

    private boolean processDuplicated(Schedule schedule, Nickname candidate) {
        Nickname next = schedule.pollFirst();
        schedule.addLast(next);
        schedule.addFirst(candidate);
        monthlySchedule.add(next);
        return true;
    }

    private boolean processWhenChanged(Nickname candidate, Schedule schedule) {
        Nickname changedNickname = schedule.pollLast();
        schedule.addLast(candidate);
        schedule.addLast(changedNickname);
        monthlySchedule.add(candidate);
        return false;
    }

    private boolean processDefaultWay(Schedule schedule, Nickname candidate) {
        schedule.addLast(candidate);
        monthlySchedule.add(candidate);
        return false;
    }

    private boolean isDuplicated(List<Nickname> schedule, Nickname candidate) {
        return !schedule.isEmpty() && schedule.getLast().equals(candidate);
    }

    private void addResult(Month month, DayOfWeek dayOfWeek, int day, List<OncallResult> oncallResults) {
        boolean isHoliday = isHoliday(dayOfWeek, month, day);
        oncallResults.add(new OncallResult(month.getValue(), day, dayOfWeek.name(), isHoliday, monthlySchedule.getLast().getValue()));
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
