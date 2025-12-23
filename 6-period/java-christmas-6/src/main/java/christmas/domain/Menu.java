package christmas.domain;

import christmas.exception.ExceptionMessage;
import java.util.Arrays;

public enum Menu {

    SOUP("양송이수프", 6_000, MenuType.APPETIZER),
    TAPAS("타파스", 5_500, MenuType.APPETIZER),
    SALAD("시저샐러드", 8_000, MenuType.APPETIZER),

    T_BONE_STEAK("티본스테이크", 55_000, MenuType.MAIN),
    RIB("바비큐립", 54_000, MenuType.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, MenuType.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MenuType.MAIN),

    CHOCO_CAKE("초코케이크", 15_000, MenuType.DESSERT),
    ICE_CREAM("아이스크림", 5_000, MenuType.DESSERT),

    ZERO_COKE("제로콜라", 3_000, MenuType.DRINK),
    RED_WINE("레드와인", 60_000, MenuType.DRINK),
    CHAMPAGNE("샴페인", 25_000, MenuType.DRINK),
    ;

    private final String name;
    private final int price;
    private final MenuType type;

    Menu(String name, int price, MenuType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public static Menu from(String name) {
        return Arrays.stream(Menu.values())
                .filter(element -> element.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.MENU_NOT_FOUND.getMessage()));
    }

    public boolean isDrink() {
        return this.type == MenuType.DRINK;
    }
}
