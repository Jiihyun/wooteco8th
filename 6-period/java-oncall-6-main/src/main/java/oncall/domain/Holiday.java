package oncall.domain;

import java.util.Arrays;

public enum Holiday {

    신정(Month.JAN, 1),
    삼일절(Month.MAR, 1),
    어린이날(Month.MAY, 5),
    현충일(Month.JUN, 6),
    광복절(Month.AUG, 15),
    개천절(Month.OCT, 3),
    한글날(Month.OCT, 9),
    성탄절(Month.DEC, 25),
    ;

    private final Month month;
    private final int day;

    Holiday(Month month, int day) {
        this.month = month;
        this.day = day;
    }

    public static boolean isHoliday(Month month, int day) {
        return Arrays.stream(Holiday.values())
                .anyMatch(holiday -> holiday.month == month && holiday.day == day);
    }
}
