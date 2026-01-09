package lotto.domain;

import java.util.Arrays;
import java.util.List;

public enum Rank {

    NONE(0, 0, false),
    FIFTH(3, 5_000, false),
    FOURTH(4, 50_000, false),
    THIRD(5, 1_500_000, false),
    SECOND(5, 30_000_000, true),
    FIRST(6, 2_000_000_000, false),
    ;

    private final int matchingCount;
    private final int prizeAmount;
    private final boolean requireBonusNumber;

    private static final List<Rank> NON_REQUIRE_BONUS_NUMBER = Arrays.stream(Rank.values())
            .filter(element -> !element.requireBonusNumber)
            .toList();

    Rank(int matchingCount, int prizeAmount, boolean requireBonusNumber) {
        this.matchingCount = matchingCount;
        this.prizeAmount = prizeAmount;
        this.requireBonusNumber = requireBonusNumber;
    }

    public static Rank of(int matchingCount, boolean hasBonusNumber) {
        if (matchingCount == SECOND.matchingCount && hasBonusNumber) {
            return SECOND;
        }
        return NON_REQUIRE_BONUS_NUMBER.stream()
                .filter(rank -> rank.matchingCount == matchingCount)
                .findAny()
                .orElse(NONE);
    }

    public int calculatePrizeAmount(int matchingCount) {
        return this.getPrizeAmount() * matchingCount;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }
}
