package lotto.domain;

import java.util.EnumMap;
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

    public Map<Rank, Integer> getHistories() {
        return histories;
    }
}
