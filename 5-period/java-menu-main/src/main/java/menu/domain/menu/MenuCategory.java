package menu.domain.menu;

import java.util.Arrays;
import menu.exception.ExceptionMessage;

public enum MenuCategory {

    JAPANESE("일식", 1),
    KOREAN("한식", 2),
    CHINESE("중식", 3),
    ASIAN("아시안", 4),
    AMERICAN("양식", 5),
    ;

    private final String description;
    private final int number;

    MenuCategory(String description, int number) {
        this.description = description;
        this.number = number;
    }

    public static MenuCategory from(int number) {
        return Arrays.stream(MenuCategory.values())
                .filter(element -> element.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.INVALID_MENU_CATEGORY.getMessage()));
    }

    public int getNumber() {
        return number;
    }
}
