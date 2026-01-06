package attendance.domain;

import attendance.exception.ExceptionMessage;
import java.time.LocalDateTime;

public class Attendance {

    private String nickname;
    private LocalDateTime dateTime;
    private AttendanceState attendanceState;

    public Attendance(String nickname, LocalDateTime dateTime, AttendanceState attendanceState) {
        this.nickname = nickname;
        this.dateTime = dateTime;
        this.attendanceState = attendanceState;
    }

    public boolean hasSameNickname(String nickname) {
        return this.nickname.equals(nickname);
    }

    public void editAttendanceState(AttendanceState attendanceState) {
        this.attendanceState = attendanceState;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public boolean exists(String nickname, LocalDateTime dateTime) {
        return this.nickname.equals(nickname)
                && this.dateTime.equals(dateTime);
    }

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
