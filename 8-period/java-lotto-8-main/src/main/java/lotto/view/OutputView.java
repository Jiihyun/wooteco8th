package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Rank;

public final class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String STATISTICS_FORMAT = """
            당첨 통계
            ---
            3개 일치 (5,000원) - %d개
            4개 일치 (50,000원) - %d개
            5개 일치 (1,500,000원) - %d개
            5개 일치, 보너스 볼 일치 (30,000,000원) - %d개
            6개 일치 (2,000,000,000원) - %d개
            총 수익률은 %,.1f%%입니다.""";

    private OutputView() {
    }

    public static void showPurchasedLottos(List<Lotto> purchasedLottos) {
        System.out.println(NEW_LINE + purchasedLottos.size() + "개를 구매했습니다.");
        purchasedLottos.stream()
                .map(Lotto::getNumbers)
                .forEach(System.out::println);
    }

    public static void showStatistics(Map<Rank, Integer> histories, double profitRate) {
        System.out.println(STATISTICS_FORMAT.formatted(
                histories.get(Rank.FIFTH),
                histories.get(Rank.FOURTH),
                histories.get(Rank.THIRD),
                histories.get(Rank.SECOND),
                histories.get(Rank.FIRST),
                profitRate
        ));
    }

    public static void showError(String message) {
        System.out.println(message);
    }
}
