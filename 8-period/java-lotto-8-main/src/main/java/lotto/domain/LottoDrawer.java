package lotto.domain;

import java.util.List;
import java.util.Map;

public class LottoDrawer {

    private final RankHistory rankHistory;
    private final WinningLotto winningLotto;

    public LottoDrawer(RankHistory rankHistory, WinningLotto winningLotto) {
        this.rankHistory = rankHistory;
        this.winningLotto = winningLotto;
    }

    public Map<Rank, Integer> draw(List<Lotto> purchasedLottos) {
        for (Lotto purchasedLotto : purchasedLottos) {
            Rank rank = winningLotto.findRank(purchasedLotto);
            rankHistory.put(rank);
        }
        return rankHistory.getHistories();
    }
}
