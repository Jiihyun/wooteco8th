package attendance.controller;

import attendance.domain.AttendanceHistory;
import attendance.domain.AttendanceProcessor;
import attendance.domain.command.Command;
import attendance.dto.AttendanceResult;
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
        AttendanceProcessor attendanceProcessor = new AttendanceProcessor(dateOfToday, attendanceHistory);
        while (true) {
            Command command = InputView.readCommand(dateOfToday);
            if (command.isQuit()) {
                return;
            }
            processCommand(command, attendanceProcessor);
        }
    }

    private void processCommand(Command command, AttendanceProcessor attendanceProcessor) {
        if (command.isCheckAttendance()) {
            checkAttendance(attendanceProcessor);
        }
        if (command.isEditAttendance()) {
            editAttendance(attendanceProcessor);
        }
        if (command.isCheckAttendancePerCrew()) {
            showAttendance(attendanceProcessor);
        }
        if (command.isCheckExpulsion()) {
            showExpulsion(attendanceProcessor);
        }
    }

    private void checkAttendance(AttendanceProcessor attendanceProcessor) {
        String nickname = readNickname(attendanceProcessor);
        LocalTime time = readTime(attendanceProcessor);
        AttendanceResult attendanceResult = attendanceProcessor.checkAttendance(nickname, time);
        OutputView.showCheckedAttendance(attendanceResult);
    }

    private String readNickname(AttendanceProcessor attendanceProcessor) {
        String nickname = InputView.readNickname();
        attendanceProcessor.validateNickname(nickname);
        return nickname;
    }

    private LocalTime readTime(AttendanceProcessor attendanceProcessor) {
        LocalTime time = InputView.readArrivedTime();
        attendanceProcessor.validateRunningTime(time);
        return time;
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

    private void showAttendance(AttendanceProcessor attendanceProcessor) {
        String nickname = InputView.readNickname();
        ShowResult showResult = attendanceProcessor.showAttendance(nickname);
        OutputView.showResult(nickname, showResult);
    }

    private void showExpulsion(AttendanceProcessor attendanceProcessor) {
        List<ShowResult> results = attendanceProcessor.findExpulsionCrews();
        OutputView.showExplusion(results);
    }
}
