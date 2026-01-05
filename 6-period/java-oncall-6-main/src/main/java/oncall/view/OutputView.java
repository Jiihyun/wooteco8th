package oncall.view;

import java.util.List;
import oncall.domain.ScheduleInfo;

public final class OutputView {

    private static final String SCHEDULE_FORMAT = "%d월 %d일 %s %s";

    private OutputView() {
    }

    public static void showSchedule(List<ScheduleInfo> scheduleInfos) {
        for (ScheduleInfo scheduleInfo : scheduleInfos) {
            System.out.println(
                    SCHEDULE_FORMAT.formatted(
                            scheduleInfo.getMonthValue(), scheduleInfo.getDay(),
                            formatDayOfWeek(scheduleInfo), scheduleInfo.getNickname()));
        }
    }

    private static String formatDayOfWeek(ScheduleInfo scheduleInfo) {
        if (scheduleInfo.isWeekdayAndHoliday()) {
            return scheduleInfo.getDayOfWeek() + "(휴일)";
        }
        return scheduleInfo.getDayOfWeek();
    }

    public static void showError(String message) {
        System.out.println(message);
    }
}
