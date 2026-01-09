package lotto.domain;

import java.util.List;
import java.util.stream.IntStream;

public class LottoStore {


    private final LottoGenerator lottoGenerator;

    public LottoStore(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public List<Lotto> sellLotto(PurchasedAmount purchasedAmount) {
        int lottoQuantity = purchasedAmount.calculateLottoQuantity();
        return IntStream.range(0, lottoQuantity)
                .mapToObj(i -> lottoGenerator.generate())
                .map(Lotto::new)
                .toList();
    }
}
