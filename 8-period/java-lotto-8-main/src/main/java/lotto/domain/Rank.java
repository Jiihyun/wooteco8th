package lotto.domain;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum Rank {

    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false),
    NONE(0, 0, false),
    ;

    private static final Set<Rank> WITHOUT_BONUS_RANK = Arrays.stream(values())
            .filter(rank -> !rank.requireBonusNumber)
            .collect(Collectors.toSet());

    private final int matchingCount;
    private final int prizeAmount;
    private final boolean requireBonusNumber;

    Rank(int matchingCount, int prizeAmount, boolean requireBonusNumber) {
        this.matchingCount = matchingCount;
        this.prizeAmount = prizeAmount;
        this.requireBonusNumber = requireBonusNumber;
    }

    public static Rank findRank(int matchingCount, boolean hasBonusNumber) {
        if (matchingCount == SECOND.matchingCount && hasBonusNumber) {
            return SECOND;
        }
        return WITHOUT_BONUS_RANK.stream()
                .filter(rank -> rank.matchingCount == matchingCount)
                .findAny()
                .orElse(NONE);
    }

    public int getMatchingCount() {
        return matchingCount;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }
}
