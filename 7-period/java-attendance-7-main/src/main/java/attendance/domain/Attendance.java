package attendance.domain;

import attendance.exception.ExceptionMessage;
import java.time.LocalDateTime;
import java.util.Objects;

public class Attendance {

    private String nickname;
    private LocalDateTime dateTime;

    public Attendance(String nickname, LocalDateTime dateTime) {
        this.nickname = nickname;
        this.dateTime = dateTime;
    }

    public boolean hasSameNickname(String nickname) {
        return this.nickname.equals(nickname);
    }

    public boolean hasSameDateTime(int dayOfMonth) {
        return this.dateTime.getDayOfMonth() == dayOfMonth;
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

    private boolean isFuture(LocalDateTime dateTime) {
        return this.dateTime.getDayOfMonth() < dateTime.getDayOfMonth();
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Attendance that)) {
            return false;
        }

        return Objects.equals(getNickname(), that.getNickname()) && Objects.equals(getDateTime(), that.getDateTime());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getNickname());
        result = 31 * result + Objects.hashCode(getDateTime());
        return result;
    }
}
