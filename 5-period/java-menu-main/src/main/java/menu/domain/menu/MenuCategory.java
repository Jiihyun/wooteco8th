package menu.domain.menu;

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
}
