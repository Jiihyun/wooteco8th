package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoStore;
import lotto.domain.PurchasedAmount;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        PurchasedAmount purchasedAmount = new PurchasedAmount(InputView.readPurchasedAmount());
        LottoStore lottoStore = new LottoStore(new LottoGenerator());
        List<Lotto> purchasedLottos = lottoStore.sellLotto(purchasedAmount);
        OutputView.showPurchasedLottos(purchasedLottos);
    }
}
