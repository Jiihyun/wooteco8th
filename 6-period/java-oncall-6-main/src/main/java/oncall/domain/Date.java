package oncall.domain;

import oncall.dto.OncallRequest;

public class Date {

    private final Month month;
    private int day;
    private DayOfWeek dayOfWeek;

    public static Date from(Date date) {
        return new Date(date.month, date.day, date.dayOfWeek);
    }

    public Date(OncallRequest request) {
        this.month = request.month();
        this.day = 1;
        this.dayOfWeek = request.dayOfWeek();
    }

    public Date(Month month, int day, DayOfWeek dayOfWeek) {
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
    }

    public boolean underEndDate() {
        return day <= month.getEndDate();
    }

    public boolean isWeekend() {
        return dayOfWeek.isWeekend();
    }

    public boolean isWeekday() {
        return !dayOfWeek.isWeekend();
    }

    public boolean isHoliday() {
        return Holiday.isHoliday(month, day);
    }

    public int getMonthValue() {
        return month.getValue();
    }

    public int getDay() {
        return day;
    }

    public String getDayOfWeek() {
        return dayOfWeek.getDescription();
    }

    public void plusDate() {
        this.day += 1;
        this.dayOfWeek = dayOfWeek.getNextDayOfWeek();
    }
}
