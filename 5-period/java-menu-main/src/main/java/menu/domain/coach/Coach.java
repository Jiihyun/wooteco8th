package menu.domain.coach;

import java.util.List;
import java.util.Objects;
import menu.domain.menu.Menu;
import menu.exception.ExceptionMessage;

public class Coach {

    private static final int MAX_MENU_SIZE = 2;

    private final Name name;
    private final List<Menu> cantEatMenus;

    public Coach(Name name, List<String> cantEatMenus) {
        validateMenu(cantEatMenus);
        this.name = name;
        this.cantEatMenus = initCantEatMenus(cantEatMenus);
    }

    private List<Menu> initCantEatMenus(List<String> cantEatMenus) {
        if (cantEatMenus.size() == 1 && cantEatMenus.getFirst().isEmpty()) {
            return List.of();
        }
        return cantEatMenus.stream()
                .map(Menu::from)
                .toList();
    }

    private void validateMenu(List<String> cantEatMenus) {
        validateSize(cantEatMenus);
        validateUniqueMenu(cantEatMenus);
    }

    private void validateSize(List<String> cantEatMenus) {
        if (isOutOfRange(cantEatMenus.size())) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_MENU_SIZE.getMessage());
        }
    }

    private boolean isOutOfRange(int size) {
        return size > MAX_MENU_SIZE;
    }

    public void validateUniqueMenu(List<String> cantEatMenus) {
        if (isDuplicated(cantEatMenus)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_MENU.getMessage());
        }
    }

    private boolean isDuplicated(List<String> cantEatMenus) {
        return cantEatMenus.stream()
                .distinct()
                .count() != cantEatMenus.size();
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Coach coach)) {
            return false;
        }

        return Objects.equals(name, coach.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
