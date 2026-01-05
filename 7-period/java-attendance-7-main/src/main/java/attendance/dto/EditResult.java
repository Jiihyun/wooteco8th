package attendance.dto;

import java.time.LocalDateTime;

public record EditResult(
        LocalDateTime before,
        LocalDateTime after
) {
}
