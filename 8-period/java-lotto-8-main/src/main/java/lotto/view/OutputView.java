package lotto.view;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.RankStatistics;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String ERROR_PREFIX = "[ERROR] ";

    public static void showLottos(List<Lotto> lottos) {
        System.out.println(NEW_LINE + lottos.size() + "개를 구매했습니다.");
        lottos.forEach(lotto -> System.out.println(sortLottoNumber(lotto)));
    }

    private static List<Integer> sortLottoNumber(Lotto lotto) {
        return lotto.getNumbers().stream()
                .map(LottoNumber::getValue)
                .sorted()
                .toList();
    }

    public static void showStatistics(RankStatistics rankStatistics, double earningRate) {
        System.out.println("당첨 통계" + NEW_LINE + "---");
        RankFormatter.orderRanks()
                .forEach(rank -> {
                    String statistics = RankFormatter.formatRank(rank, rankStatistics.getMatchingCountByRank(rank));
                    System.out.println(statistics);
                });
        System.out.println("총 수익률은 %,.1f%%입니다.".formatted(earningRate));
    }

    public static void showError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }
}
