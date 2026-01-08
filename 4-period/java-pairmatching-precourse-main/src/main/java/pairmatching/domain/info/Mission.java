package pairmatching.domain.info;

import java.util.Arrays;
import pairmatching.exception.ExceptionMessage;

public enum Mission {

    자동차경주(Level.레벨1, "자동차경주"),
    로또(Level.레벨1, "로또"),
    숫자야구게임(Level.레벨1, "숫자야구게임"),

    장바구니(Level.레벨2, "장바구니"),
    결제(Level.레벨2, "결제"),
    지하철노선도(Level.레벨2, "지하철노선도"),

    성능개선(Level.레벨4, "성능개선"),
    배포(Level.레벨4, "배포"),
    ;

    private final Level level;
    private final String description;

    Mission(Level level, String description) {
        this.level = level;
        this.description = description;
    }

    public static Mission from(String level, String description) {
        return Arrays.stream(Mission.values())
                .filter(element -> element.level == Level.from(level))
                .filter(element -> element.description.equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.MISSION_NOT_FOUND.getMessage()));
    }
}
