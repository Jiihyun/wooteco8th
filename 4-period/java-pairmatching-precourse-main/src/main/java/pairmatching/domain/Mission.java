package pairmatching.domain;

import java.util.Arrays;
import pairmatching.exception.ExceptionMessage;

public enum Mission {

    RACING(Level.LEVEL1, "자동차경주"),
    LOTTO(Level.LEVEL1, "로또"),
    BASEBALL(Level.LEVEL1, "숫자야구게임"),
    CART(Level.LEVEL2, "장바구니"),
    PAYMENT(Level.LEVEL1, "결제"),
    SUBWAY(Level.LEVEL2, "지하철노선도"),
    PERFORMANCE_IMPROVEMENT(Level.LEVEL4, "성능개선"),
    DISTRIBUTION(Level.LEVEL4, "배포"),
    ;

    private Level level;
    private String name;

    Mission(Level level, String name) {
        this.level = level;
        this.name = name;
    }

    public static Mission from(String input) {
        return Arrays.stream(Mission.values())
                .filter(element -> element.name.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.MISSION_NOT_FOUND.getMessage()));
    }
}
