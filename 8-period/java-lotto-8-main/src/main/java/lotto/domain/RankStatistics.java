package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class RankStatistics {

    private final Map<Rank, Integer> statistics;

    public RankStatistics() {
        this.statistics = initialize();
    }

    private Map<Rank, Integer> initialize() {
        Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }
        return statistics;
    }

    public void put(Rank rank) {
        statistics.put(rank, statistics.get(rank) + 1);
    }

    public Map<Rank, Integer> getStatistics() {
        return statistics;
    }
}
