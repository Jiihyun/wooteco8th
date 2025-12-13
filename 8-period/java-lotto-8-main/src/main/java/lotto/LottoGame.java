package lotto;

import static lotto.domain.LottoStore.LOTTO_PRICE_UNIT;

import java.util.List;
import lotto.domain.EarningRateCalculator;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumberGenerator;
import lotto.domain.LottoStore;
import lotto.domain.RankStatistics;
import lotto.domain.WinningLotto;
import lotto.util.RetryHandler;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {

    public void run() {
        List<Lotto> lottos = RetryHandler.retryOnInvalidInput(this::purchaseLotto);
        WinningLotto winningLotto = RetryHandler.retryOnInvalidInput(this::readWinningLotto);
        RankStatistics rankStatistics = calculateRankStatistics(lottos, winningLotto);
        double earningRate = calculateEarningRate(rankStatistics, lottos);
        OutputView.showStatistics(rankStatistics, earningRate);
    }

    private List<Lotto> purchaseLotto() {
        int purchasedAmount = InputView.readPurchasedAmount();
        LottoStore lottoStore = new LottoStore(new LottoNumberGenerator());
        List<Lotto> lottos = lottoStore.sell(purchasedAmount);
        OutputView.showLottos(lottos);
        return lottos;
    }

    private WinningLotto readWinningLotto() {
        List<Integer> winningNumbers = RetryHandler.retryOnInvalidInput(InputView::readWinningNumbers);
        int bonusNumber = InputView.readBonusNumber();
        return new WinningLotto(new Lotto(winningNumbers), new LottoNumber(bonusNumber));
    }

    private RankStatistics calculateRankStatistics(List<Lotto> lottos, WinningLotto winningLotto) {
        RankStatistics rankStatistics = new RankStatistics();
        rankStatistics.calculate(winningLotto, lottos);
        return rankStatistics;
    }

    private double calculateEarningRate(RankStatistics rankStatistics, List<Lotto> lottos) {
        EarningRateCalculator earningRateCalculator = new EarningRateCalculator(rankStatistics);
        return earningRateCalculator.calculateEarningRate(lottos.size() * LOTTO_PRICE_UNIT);
    }
}
