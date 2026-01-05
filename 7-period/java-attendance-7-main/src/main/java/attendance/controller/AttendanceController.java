package attendance.controller;

import attendance.domain.AttendanceHistory;
import attendance.domain.AttendanceProcessor;
import attendance.domain.command.Command;
import attendance.dto.EditResult;
import attendance.util.ExceptionHandler;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AttendanceController {

    public void run() {
        LocalDate dateOfToday = DateTimes.now().toLocalDate();
        AttendanceHistory attendanceHistory = new AttendanceHistory();
        AttendanceProcessor attendanceProcessor = new AttendanceProcessor(attendanceHistory);
        process(dateOfToday, attendanceProcessor);
    }

    private void process(LocalDate dateOfToday, AttendanceProcessor attendanceProcessor) {
        while (true) {
            Command command = ExceptionHandler.showError(() -> InputView.readCommand(dateOfToday));
            if (command.isCheckAttendance()) {
                ExceptionHandler.showError(() -> checkAttendance(attendanceProcessor, dateOfToday));
            }
            if (command.isEditAttendance()) {
                ExceptionHandler.showError(() -> editAttendance(attendanceProcessor));
            }
            if (command.isQuit()) {
                return;
            }
        }
    }

    private void checkAttendance(AttendanceProcessor attendanceProcessor, LocalDate dateOfToday) {
        String nickname = ExceptionHandler.showError(() -> InputView.readNickname());
        LocalDateTime dateTime = ExceptionHandler.showError(() -> LocalDateTime.of(dateOfToday, InputView.readArrivedTime()));
        attendanceProcessor.checkAttendance(nickname, dateTime);
        OutputView.showCheckedAttendance(dateTime);
    }

    private void editAttendance(AttendanceProcessor attendanceProcessor) {
        String nickname = ExceptionHandler.showError(() -> InputView.readEditedNickname());
        LocalDate date = ExceptionHandler.showError(() -> InputView.readDayForEdit());
        LocalTime time = ExceptionHandler.showError(() -> InputView.readEditedTime());
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        EditResult editResult = ExceptionHandler.showError(() -> attendanceProcessor.editAttendance(nickname, dateTime));
        OutputView.showEditedAttendance(editResult);
    }
}
