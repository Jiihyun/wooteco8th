package subway.dto;

import java.util.List;

public record SearchedResult(
        int distance,
        int time,
        List<String> stations
) {
}
