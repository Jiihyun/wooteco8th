package attendance.controller;

import attendance.domain.AttendanceHistory;
import attendance.domain.AttendanceProcessor;
import attendance.domain.AttendanceState;
import attendance.domain.command.Command;
import attendance.dto.EditResult;
import attendance.dto.ShowResult;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class AttendanceController {

    public void run() {
        LocalDate dateOfToday = DateTimes.now().toLocalDate();
        AttendanceHistory attendanceHistory = new AttendanceHistory();
        AttendanceProcessor attendanceProcessor = new AttendanceProcessor(attendanceHistory);
        attendanceProcessor.validateWeekDay(dateOfToday);
        while (true) {
            Command command = InputView.readCommand(dateOfToday);
            if (command.isCheckAttendance()) {
                checkAttendance(attendanceProcessor, dateOfToday);
            }
            if (command.isEditAttendance()) {
                editAttendance(attendanceProcessor);
            }
            if (command.isCheckAttendancePerCrew()) {
                showAttendance(attendanceProcessor, dateOfToday);
            }
            if (command.isCheckExpulsion()) {
                showExpulsion(attendanceProcessor, dateOfToday);
            }
            if (command.isQuit()) {
                return;
            }
        }
    }

    private void showExpulsion(AttendanceProcessor attendanceProcessor, LocalDate dateOfToday) {
        List<ShowResult> results = attendanceProcessor.findExpulsionCrews(dateOfToday);
        OutputView.showExplusion(results);
    }

    private void showAttendance(AttendanceProcessor attendanceProcessor, LocalDate dateOfToday) {
        String nickname = InputView.readNickname();
        ShowResult showResult = attendanceProcessor.showAttendance(nickname, dateOfToday);
        OutputView.showResult(nickname, showResult);
    }

    private void editAttendance(AttendanceProcessor attendanceProcessor) {
        String nickname = InputView.readEditedNickname();
        attendanceProcessor.validateNickname(nickname);
        LocalDate date = InputView.readDayForEdit();
        LocalTime time = InputView.readEditedTime();
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        EditResult editResult = attendanceProcessor.editAttendance(nickname, dateTime);
        OutputView.showEditedAttendance(editResult);
    }

    private void checkAttendance(AttendanceProcessor attendanceProcessor, LocalDate dateOfToday) {
        String nickname = InputView.readNickname();
        attendanceProcessor.validateNickname(nickname);
        LocalDateTime dateTime = LocalDateTime.of(dateOfToday, InputView.readArrivedTime());
        AttendanceState attendanceState = attendanceProcessor.checkAttendance(nickname, dateTime);
        OutputView.showCheckedAttendance(dateTime, attendanceState);
    }
}
