package oncall.controller;

import java.util.List;
import oncall.domain.Date;
import oncall.domain.Schedule;
import oncall.domain.ScheduleInfo;
import oncall.domain.ScheduleProcessor;
import oncall.util.RetryHandler;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OncallController {

    public void run() {
        Date date = RetryHandler.retryOnInvalidInput(() -> new Date(InputView.readMonthAndStartDay()));
        Schedule schedule = RetryHandler.retryOnInvalidInput(this::createSchedule);
        ScheduleProcessor scheduleProcessor = new ScheduleProcessor(date, schedule);

        List<ScheduleInfo> scheduleInfos = scheduleProcessor.process();
        OutputView.showSchedule(scheduleInfos);
    }

    private Schedule createSchedule() {
        List<String> weekdayCrews = InputView.readWeekdaySchedule();
        List<String> weekendCrews = InputView.readWeekendSchedule();
        return new Schedule(weekdayCrews, weekendCrews);
    }
}
