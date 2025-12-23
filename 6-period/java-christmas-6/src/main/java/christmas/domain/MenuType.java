package christmas.domain;

public enum MenuType {

    APPETIZER("에피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료"),
    ;

    private final String description;

    MenuType(String description) {
        this.description = description;
    }
}
