package lotto.domain;

import java.util.Map;

public class EarningRateCalculator {

    public static final int PERCENTAGE = 100;
    private final RankStatistics rankStatistics;

    public EarningRateCalculator(RankStatistics rankStatistics) {
        this.rankStatistics = rankStatistics;
    }

    public double calculateEarningRate(int purchasedAmount) {
        double prizeAmount = 0;
        for (Map.Entry<Rank, Integer> entry : rankStatistics.getStatistics().entrySet()) {
            if (entry.getValue() > 0) {
                prizeAmount += entry.getKey().getPrizeAmount();
            }
        }
        return prizeAmount / purchasedAmount * PERCENTAGE;
    }
}
