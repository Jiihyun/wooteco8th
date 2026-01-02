package oncall.dto;

import oncall.domain.DayOfWeek;

public record OncallRequest(
        int month,
        DayOfWeek dayOfWeek
) {
    public static OncallRequest from(int month, String dayOfWeek) {
        return new OncallRequest(month, DayOfWeek.from(dayOfWeek));
    }
}
