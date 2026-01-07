package oncall.dto;

import oncall.domain.DayOfWeek;
import oncall.domain.Month;

public record OncallRequest(
        Month dayOfMonth,
        DayOfWeek dayOfWeek
) {
    public static OncallRequest of(int dayOfMonth, String dayOfWeek) {
        return new OncallRequest(
                Month.from(dayOfMonth),
                DayOfWeek.from(dayOfWeek));
    }
}
