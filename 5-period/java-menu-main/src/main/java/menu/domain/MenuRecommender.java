package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
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

    private boolean canRecommendCategory(List<MenuCategory> categories, MenuCategory otherMenuCategory) {
        return categories.stream()
                .filter(menuCategory -> menuCategory == otherMenuCategory)
                .count() < MAX_SAME_CATEGORY_SIZE;

    }
}
