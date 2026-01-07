package oncall.controller;

import java.util.List;
import oncall.domain.MonthlySchedule;
import oncall.domain.Schedule;
import oncall.domain.ScheduleProcessor;
import oncall.dto.OncallRequest;
import oncall.dto.OncallResult;
import oncall.util.RetryHandler;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OncallController {

    public void run() {
        OncallRequest oncallRequest = RetryHandler.retryOnInvalidInput(InputView::readMonthAndDay);

        ScheduleProcessor scheduleProcessor = RetryHandler.retryOnInvalidInput(this::createScheduleProcessor);
        List<OncallResult> oncallResults = scheduleProcessor.process(oncallRequest.dayOfMonth(), oncallRequest.dayOfWeek());
        OutputView.showSchedule(oncallResults);
    }

    private ScheduleProcessor createScheduleProcessor() {
        Schedule weekdaySchedule = RetryHandler.retryOnInvalidInput(() -> new Schedule(InputView.readWeekdaySchedule()));
        Schedule weekendSchedule = new Schedule(InputView.readWeekendSchedule());
        MonthlySchedule monthlySchedule = new MonthlySchedule(weekdaySchedule, weekendSchedule);
        return new ScheduleProcessor(monthlySchedule);
    }
}
