package pairmatching.view;

import pairmatching.domain.Pair;
import pairmatching.domain.Pairs;

public final class OutputView {

    public static final String MATCHING_RESULT_DELIMITER = " : ";

    private OutputView() {
    }

    public static void showMatcingResult(Pairs pairs) {
        System.out.println("페어 매칭 결과입니다.");
        for (Pair pair : pairs.getPairs()) {
            System.out.println(String.join(MATCHING_RESULT_DELIMITER, pair.getPairCrew()));
        }
    }
}
