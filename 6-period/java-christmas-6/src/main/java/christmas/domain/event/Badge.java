package christmas.domain.event;

import java.util.Arrays;

public enum Badge {

    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE("없음", 0),
    ;

    private final String name;
    private final int benefitAmount;

    Badge(String name, int benefitAmount) {
        this.name = name;
        this.benefitAmount = benefitAmount;
    }

    public static Badge from(int benefitAmount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.benefitAmount <= benefitAmount)
                .findFirst()
                .orElse(Badge.NONE);
    }

    public String getName() {
        return name;
    }
}
