package lotto.domain;

import java.util.List;

public class LottoDrawer {

    private final RankHistory rankHistory;
    private final WinningLotto winningLotto;

    public LottoDrawer(RankHistory rankHistory, WinningLotto winningLotto) {
        this.rankHistory = rankHistory;
        this.winningLotto = winningLotto;
    }

    public void draw(List<Lotto> purchasedLottos) {
        for (Lotto purchasedLotto : purchasedLottos) {
            Rank rank = winningLotto.findRank(purchasedLotto);
            rankHistory.put(rank);
        }
    }
}
