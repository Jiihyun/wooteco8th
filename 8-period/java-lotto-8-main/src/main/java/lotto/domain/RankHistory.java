package lotto.domain;

import static lotto.domain.PurchasedAmount.LOTTO_PRICE;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class RankHistory {

    private final Map<Rank, Integer> histories;

    public RankHistory() {
        this.histories = init();
    }

    private Map<Rank, Integer> init() {
        Map<Rank, Integer> histories = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            histories.put(rank, 0);
        }
        return histories;
    }

    public void put(Rank rank) {
        histories.put(rank, histories.get(rank) + 1);
    }

    public double calculateProfit(List<Lotto> purchasedLottos) {
        double purchasedAmount = (double) purchasedLottos.size() * LOTTO_PRICE;
        double prizeAmount = 0;
        for (Map.Entry<Rank, Integer> entry : histories.entrySet()) {
            prizeAmount += entry.getKey().calculatePrizeAmount(entry.getValue());
        }
        return prizeAmount / purchasedAmount * 100;
    }

    public Map<Rank, Integer> getHistories() {
        return histories;
    }
}
