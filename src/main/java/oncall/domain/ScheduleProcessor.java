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
        DayOfWeek dayOfWeek = startDayOfWeek;
        for (int day = 1; day <= month.getEndOfMonth(); day++) {
            putSchedule(month, dayOfWeek, day);
            addResult(month, dayOfWeek, day, oncallResults);
            dayOfWeek = getNextDayOfweek(dayOfWeek);
        }
        return oncallResults;
    }

    private void putSchedule(Month month, DayOfWeek dayOfWeek, int day) {
        Nickname candidate;
        if (isWeekend(dayOfWeek, month, day)) {
            candidate = schedules.getWeekendSchedule().pollFirst();
            process(candidate, schedules.getWeekendSchedule());
            return;
        }
        candidate = schedules.getWeekdaySchedule().pollFirst();
        process(candidate, schedules.getWeekdaySchedule());
    }

    private void process(Nickname candidate, Schedule schedules) {
        if (isDuplicated(monthlySchedule, candidate)) {
            processDuplicated(schedules, candidate);
            return;
        }
        processDefaultWay(schedules, candidate);
    }

    private void processDuplicated(Schedule schedule, Nickname candidate) {
        Nickname next = schedule.pollFirst();
        schedule.addLast(next);
        schedule.addFirst(candidate);
        monthlySchedule.add(next);
    }

    private void processDefaultWay(Schedule schedule, Nickname candidate) {
        schedule.addLast(candidate);
        monthlySchedule.add(candidate);
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
