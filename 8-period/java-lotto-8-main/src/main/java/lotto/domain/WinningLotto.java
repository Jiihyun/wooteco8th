package lotto.domain;

import java.util.List;
import lotto.exception.ExceptionMessage;

public class WinningLotto {

    private final Lotto winningNumber;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningNumber, LottoNumber bonusNumber) {
        validateUniqueNumber(winningNumber.getNumbers(), bonusNumber.getValue());
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    private void validateUniqueNumber(List<Integer> numbers, int value) {
        if (numbers.contains(value)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_NUMBER.getMessage());
        }
    }
}
