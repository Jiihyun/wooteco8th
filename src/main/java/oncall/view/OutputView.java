package oncall.view;

import java.util.List;
import oncall.dto.OncallResult;

public final class OutputView {

    private static final String WEEKDAY_FORMAT = "%d월 %d일 %s %s";
    private static final String HOLIDAY_FORMAT = "%d월 %d일 %s(휴일) %s";

    private OutputView() {
    }

    public static void showSchedule(List<OncallResult> oncallResults) {
        for (OncallResult oncallResult : oncallResults) {
            if (oncallResult.isHoliday()) {
                showResult(HOLIDAY_FORMAT, oncallResult);
                continue;
            }
            showResult(WEEKDAY_FORMAT, oncallResult);
        }
    }

    private static void showResult(String format, OncallResult oncallResult) {
        System.out.println(format.formatted(
                oncallResult.month(),
                oncallResult.day(),
                oncallResult.dayOfWeek(),
                oncallResult.nickname()));
    }
}
