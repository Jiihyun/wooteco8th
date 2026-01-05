package attendance.controller;

import attendance.view.InputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;

public class AttendanceController {

    public void run() {
        LocalDate dateOfToday = DateTimes.now().toLocalDate();
        InputView.readCommand(dateOfToday);

    }
}
