package pairmatching.view;

import pairmatching.domain.Pairs;

public final class OutputView {

    private static final String RESULT_DELIMITER = " : ";

    private OutputView() {
    }

    public static void showPairs(Pairs pairs) {
        System.out.println("페어 매칭 결과입니다.");
        pairs.getPairs().stream()
                .map(pair -> String.join(RESULT_DELIMITER, pair.getPair()))
                .forEach(System.out::println);
    }
}
