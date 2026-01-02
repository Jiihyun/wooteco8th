package oncall.controller;

import java.util.List;
import oncall.domain.Crews;
import oncall.domain.Date;
import oncall.domain.Nickname;
import oncall.domain.ScheduleInfo;
import oncall.domain.ScheduleProcessor;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OncallController {

    public void run() {
        Date date = new Date(InputView.readMonthAndStartDay());
        Crews weekdayCrews = new Crews(InputView.readWeekdaySchedule().stream()
                .map(Nickname::new)
                .toList());
        Crews weekendCrews = new Crews(InputView.readWeekendSchedule().stream()
                .map(Nickname::new)
                .toList());
        ScheduleProcessor scheduleProcessor = new ScheduleProcessor(date, weekdayCrews, weekendCrews);
        List<ScheduleInfo> scheduleInfos = scheduleProcessor.process();
        OutputView.showSchedule(scheduleInfos);
    }
}
