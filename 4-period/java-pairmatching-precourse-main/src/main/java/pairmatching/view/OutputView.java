package pairmatching.view;

import pairmatching.domain.MatchingResult;
import pairmatching.domain.Pair;
import pairmatching.domain.Pairs;

public final class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    public static final String MATCHING_RESULT_DELIMITER = " : ";

    private OutputView() {
    }

    public static void showPairs(Pairs pairs) {
        System.out.println(NEW_LINE + "페어 매칭 결과입니다.");
        for (Pair pair : pairs.getPairs()) {
            System.out.println(String.join(MATCHING_RESULT_DELIMITER, pair.getPairCrew()));
        }
    }

    public static void showMatchingResult(MatchingResult matchingResult) {
        System.out.println(NEW_LINE + "페어 매칭 결과입니다.");
        for (Pair pair : matchingResult.getPairs().getPairs()) {
            System.out.println(String.join(MATCHING_RESULT_DELIMITER, pair.getPairCrew()));
        }
    }

    public static void showResetMessage() {
        System.out.println("초기화 되었습니다.");
    }

    public static void showError(String message) {
        System.out.println(message);
    }
}
