package oncall.dto;

public record OncallResult(
        int month,
        int day,
        String dayOfWeek,
        boolean isHoliday,
        String nickname
) {
}
