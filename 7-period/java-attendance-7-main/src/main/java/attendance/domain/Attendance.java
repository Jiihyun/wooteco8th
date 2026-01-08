package attendance.domain;

import attendance.exception.ExceptionMessage;
import java.time.LocalDateTime;

public class Attendance {

    private LocalDateTime dateTime;
    private AttendanceState attendanceState;

    public Attendance(LocalDateTime dateTime, AttendanceState attendanceState) {
        this.dateTime = dateTime;
        this.attendanceState = attendanceState;
    }

    public void editAttendanceState(AttendanceState attendanceState) {
        this.attendanceState = attendanceState;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getDayOfMonth() {
        return dateTime.getDayOfMonth();
    }

//    public boolean exists(String nickname, LocalDateTime dateTime) {
//        return this.nickname.equals(nickname)
//                && this.hasSameDay(dateTime.getDayOfMonth());
//    }

    public void editDateTime(LocalDateTime dateTime) {
        if (isFuture(dateTime)) {
            throw new IllegalArgumentException(ExceptionMessage.CANNOT_EDIT.getMessage());
        }
        this.dateTime = dateTime;
    }

    public boolean hasSameDay(int day) {
        return this.dateTime.getDayOfMonth() == day;
    }

    private boolean isFuture(LocalDateTime dateTime) {
        return this.dateTime.getDayOfMonth() < dateTime.getDayOfMonth();
    }

    public AttendanceState getAttendanceState() {
        return attendanceState;
    }
}
