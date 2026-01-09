package lotto.domain;

import java.util.List;
import java.util.stream.IntStream;

public class LottoStore {

    private static final int LOTTO_PRICE = 1_000;

    public List<Lotto> sellLotto(PurchasedAmount purchasedAmount) {
        //TODO: 게터 리팩토링
        int lottoQuantity = purchasedAmount.getValue() / LOTTO_PRICE;
        return IntStream.range(0, lottoQuantity)
                .mapToObj(i -> LottoGenerator.generate())
                .map(Lotto::new)
                .toList();
    }
}
