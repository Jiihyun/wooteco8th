package pairmatching.view;

import pairmatching.domain.Pairs;

public final class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String RESULT_DELIMITER = " : ";

    private OutputView() {
    }

    public static void showPairs(Pairs pairs) {
        System.out.println(NEW_LINE + "페어 매칭 결과입니다.");
        pairs.getPairs().stream()
                .map(pair -> String.join(RESULT_DELIMITER, pair.getPair()))
                .forEach(System.out::println);
    }

    public static void showClear() {
        System.out.println("초기화 되었습니다.");
    }

    public static void showError(String message) {
        System.out.println(message);
    }
}
