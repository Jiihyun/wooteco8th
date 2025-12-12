package lotto.view;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

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

}
