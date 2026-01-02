package oncall.dto;

import oncall.domain.DayOfWeek;
import oncall.domain.Month;

public record OncallRequest(
        Month month,
        DayOfWeek dayOfWeek
) {
    public static OncallRequest from(int month, String dayOfWeek) {
        return new OncallRequest(Month.from(month), DayOfWeek.from(dayOfWeek));
    }
}
