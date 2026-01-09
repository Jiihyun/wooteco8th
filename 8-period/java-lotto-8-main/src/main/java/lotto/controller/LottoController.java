package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoDrawer;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoNumber;
import lotto.domain.LottoStore;
import lotto.domain.PurchasedAmount;
import lotto.domain.RankHistory;
import lotto.domain.WinningLotto;
import lotto.util.RetryHandler;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        PurchasedAmount purchasedAmount = RetryHandler.retryOnInvalidInput(this::readPurchasedAmount);
        LottoStore lottoStore = new LottoStore(new LottoGenerator());
        List<Lotto> purchasedLottos = lottoStore.sellLotto(purchasedAmount);
        OutputView.showPurchasedLottos(purchasedLottos);

        WinningLotto winningLotto = RetryHandler.retryOnInvalidInput(this::readWinningLotto);

        RankHistory rankHistory = new RankHistory();
        LottoDrawer lottoDrawer = new LottoDrawer(rankHistory, winningLotto);
        lottoDrawer.draw(purchasedLottos);

        double profitRate = rankHistory.calculateProfit(purchasedAmount.getValue());
        OutputView.showStatistics(rankHistory.getHistories(), profitRate);
    }

    private PurchasedAmount readPurchasedAmount() {
        int purchasedAmount = InputView.readPurchasedAmount();
        return new PurchasedAmount(purchasedAmount);
    }

    private WinningLotto readWinningLotto() {
        Lotto winningNumber = RetryHandler.retryOnInvalidInput(() -> new Lotto(InputView.readWinningNumber()));
        LottoNumber bonusNumber = RetryHandler.retryOnInvalidInput(() -> new LottoNumber(InputView.readBonusNumber()));
        return new WinningLotto(winningNumber, bonusNumber);
    }
}
