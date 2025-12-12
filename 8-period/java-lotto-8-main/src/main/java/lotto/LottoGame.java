package lotto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumberGenerator;
import lotto.domain.LottoStore;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {

    public void run() {
        int purchasedAmount = InputView.readPurchasedAmount();
        LottoStore lottoStore = new LottoStore(new LottoNumberGenerator());
        List<Lotto> lottos = lottoStore.sell(purchasedAmount);
        OutputView.showLottos(lottos);
        List<Integer> winningNumbers = InputView.readWinningNumbers();
    }
}
