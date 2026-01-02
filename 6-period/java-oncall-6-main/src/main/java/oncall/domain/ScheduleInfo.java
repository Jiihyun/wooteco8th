package oncall.domain;

public class ScheduleInfo {

    private final Date date;
    private final Nickname nickname;

    public ScheduleInfo(Date date, Nickname nickname) {
        this.date = date;
        this.nickname = nickname;
    }

    public boolean isWeekdayAndHoliday() {
        return date.isWeekday() && date.isHoliday();
    }

    public boolean hasDuplicateSchedule(Nickname nickname) {
        return this.nickname.equals(nickname);
    }

    public int getMonthValue() {
        return date.getMonthValue();
    }

    public int getDay() {
        return date.getDay();
    }

    public String getDayOfWeek() {
        return date.getDayOfWeek();
    }

    public String getNickname() {
        return nickname.getValue();
    }
}
