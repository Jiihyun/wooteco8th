package lotto.domain;

import lotto.exception.ExceptionMessage;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningNumbers, LottoNumber bonusNumber) {
        validateBonusNumber(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(Lotto winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.hasSameNumber(bonusNumber)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_LOTTO_NUMBER.getMessage());
        }
    }

    public int calculateMatchingCount(Lotto purchasedLotto) {
        return purchasedLotto.calculateMatchingCount(winningNumbers);
    }

    public boolean checkBonusNumberBy(Lotto purchasedLotto) {
        return purchasedLotto.hasSameNumber(bonusNumber);
    }
}
