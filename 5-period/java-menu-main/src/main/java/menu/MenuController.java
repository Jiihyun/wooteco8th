package menu;

import java.util.List;
import menu.domain.MenuRecommender;
import menu.domain.coach.Coach;
import menu.domain.coach.Coaches;
import menu.domain.coach.Names;
import menu.domain.menu.MenuCategory;
import menu.util.RetryHandler;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuController {

    public void run() {
        OutputView.showIntro();
        Names names = RetryHandler.retryOnInvalidInput(() -> new Names(InputView.readCoachName()));
        Coaches coaches = RetryHandler.retryOnInvalidInput(() -> createCoaches(names));
        MenuRecommender menuRecommender = new MenuRecommender();
        List<MenuCategory> categories = menuRecommender.recommendCategory();
        OutputView.showCategory(categories);
        recommendMenu(categories, menuRecommender, coaches);
        showRecommendedMenu(coaches);
        OutputView.showOutro();
    }

    private Coaches createCoaches(Names names) {
        return new Coaches(names.getNames().stream()
                .map(name -> {
                    List<String> cantEatMenus = InputView.readCantEatMenu(name.getValue());
                    return new Coach(name, cantEatMenus);
                })
                .toList());
    }

    private void recommendMenu(List<MenuCategory> categories, MenuRecommender menuRecommender, Coaches coaches) {
        categories.forEach(category -> menuRecommender.recommendMenu(category, coaches));
    }

    private void showRecommendedMenu(Coaches coaches) {
        coaches.getCoaches().forEach(
                coach -> OutputView.showRecommendedMenu(coach.getName(), coach.getRecommendedMenu()));
    }
}
