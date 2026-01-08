package attendance.dto;

import attendance.domain.Expulsion;

public record ExpulsionResult(
        String nickname,
        int lateCount,
        int absenceCount,
        Expulsion expulsion
) {
}
