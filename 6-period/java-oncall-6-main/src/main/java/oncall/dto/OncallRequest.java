package oncall.dto;

import oncall.domain.DayOfWeek;

public record OncallRequest(
        int month,
        DayOfWeek dayOfWeek
) {
}
