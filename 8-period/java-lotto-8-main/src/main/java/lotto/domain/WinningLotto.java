package lotto.domain;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningNumbers, LottoNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public int calculateMatchingCount(Lotto purchasedLotto) {
        return purchasedLotto.calculateMatchingCount(winningNumbers);
    }

    public boolean checkBonusNumberBy(Lotto purchasedLotto) {
        return purchasedLotto.hasSameNumber(bonusNumber);
    }
}
