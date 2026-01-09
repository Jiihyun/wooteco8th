package lotto.view;

import java.util.List;
import lotto.domain.Lotto;

public final class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    private OutputView() {
    }

    public static void showPurchasedLottos(List<Lotto> purchasedLottos) {
        System.out.println(NEW_LINE + purchasedLottos.size() + "개를 구매했습니다.");
        purchasedLottos.stream()
                .map(Lotto::getNumbers)
                .forEach(System.out::println);
    }
}
