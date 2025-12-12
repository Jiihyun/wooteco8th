package lotto.view;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Rank;
import lotto.domain.RankStatistics;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String STATISTICS_FORMAT = """
            당첨 통계
            ---
            3개 일치 (5,000원) - %d개
            4개 일치 (50,000원) - %d개
            5개 일치 (1,500,000원) - %d개
            5개 일치, 보너스 볼 일치 (30,000,000원) - %d개
            6개 일치 (2,000,000,000원) - %d개
            총 수익률은 %,.1f%%입니다.""";

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
        System.out.println(STATISTICS_FORMAT.formatted(
                rankStatistics.getStatistics().get(Rank.FIFTH),
                rankStatistics.getStatistics().get(Rank.FOURTH),
                rankStatistics.getStatistics().get(Rank.THIRD),
                rankStatistics.getStatistics().get(Rank.SECOND),
                rankStatistics.getStatistics().get(Rank.FIRST),
                earningRate
        ));
    }

    public static void showError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }
}
