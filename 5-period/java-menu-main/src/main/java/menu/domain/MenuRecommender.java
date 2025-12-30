package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import menu.domain.coach.Coach;
import menu.domain.coach.Coaches;
import menu.domain.menu.Menu;
import menu.domain.menu.MenuCategory;

public class MenuRecommender {

    private static final int CATEGORY_SIZE = 5;
    private static final int MAX_SAME_CATEGORY_SIZE = 2;

    public List<MenuCategory> recommendCategory() {
        List<MenuCategory> categories = new ArrayList<>();
        while (keepRecommend(categories)) {
            int categoryNumber = Randoms.pickNumberInRange(MenuCategory.JAPANESE.getNumber(), MenuCategory.AMERICAN.getNumber());
            MenuCategory menuCategory = MenuCategory.from(categoryNumber);
            if (canRecommendCategory(categories, menuCategory)) {
                categories.add(menuCategory);
            }
        }
        return categories;
    }

    private boolean keepRecommend(List<MenuCategory> categories) {
        return categories.size() < CATEGORY_SIZE;
    }

    private boolean canRecommendCategory(List<MenuCategory> categories, MenuCategory category) {
        return Collections.frequency(categories, category) < MAX_SAME_CATEGORY_SIZE;
    }

    public void recommendMenu(MenuCategory menuCategory, Coaches coaches) {
        List<String> sameCategoryMenus = Menu.findSameCategoryMenus(menuCategory);
        for (Coach coach : coaches.getCoaches()) {
            Menu menu = Menu.from(Randoms.shuffle(sameCategoryMenus).get(0));
            while (coach.cannotEat(menu) && coach.containsRecommendMenu(menu)) {
                menu = Menu.from(Randoms.shuffle(sameCategoryMenus).get(0));
            }
            coach.addRecommendedMenu(menu);
        }
    }
}
