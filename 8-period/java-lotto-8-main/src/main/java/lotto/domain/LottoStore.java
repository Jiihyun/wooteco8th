package lotto.domain;

import java.util.List;
import java.util.stream.IntStream;
import lotto.exception.ExceptionMessage;

public class LottoStore {

    private static final int LOTTO_PRICE_UNIT = 1_000;

    private LottoNumberGenerator lottoNumberGenerator;

    public LottoStore(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public List<Lotto> sell(int purchasedAmount) {
        validatePurchasedAmount(purchasedAmount);
        int quantity = purchasedAmount / LOTTO_PRICE_UNIT;
        return IntStream.range(0, quantity)
                .mapToObj(i -> createLotto())
                .toList();
    }

    private void validatePurchasedAmount(int purchasedAmount) {
        if (purchasedAmount % LOTTO_PRICE_UNIT != 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_PURCHASED_AMOUNT_UNIT.getMessage());
        }
    }

    private Lotto createLotto() {
        return new Lotto(lottoNumberGenerator.generate());
    }
}
