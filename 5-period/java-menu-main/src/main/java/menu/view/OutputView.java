package menu.view;

import java.util.ArrayList;
import java.util.List;
import menu.domain.menu.Menu;
import menu.domain.menu.MenuCategory;

public final class OutputView {

    private OutputView() {
    }

    public static void showIntro() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
    }

    public static void showCategory(List<MenuCategory> categories) {
        System.out.println("""
                
                메뉴 추천 결과입니다.
                [ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]""");
        List<String> result = new ArrayList<>();
        result.add("카테고리");
        for (MenuCategory category : categories) {
            String description = category.getDescription();
            result.add(description);
        }
        System.out.println("[ " + String.join(" | ", result) + " ]");
    }

    public static void showRecommendedMenu(String name, List<Menu> menus) {
        List<String> result = new ArrayList<>();
        result.add(name);
        for (Menu menu : menus) {
            result.add(menu.getName());
        }
        System.out.println("[ " + String.join(" | ", result) + " ]");
    }

    public static void showOutro() {
        System.out.println("추천을 완료했습니다.");
    }

    public static void showError(String message) {
        System.out.println(message);
    }
}
