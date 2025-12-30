package menu.domain.coach;

import java.util.List;
import java.util.Objects;
import menu.domain.menu.Menu;

public class Coach {

    private final Name name;
    private final List<Menu> cantEatMenus;

    public Coach(Name name, List<String> cantEatMenus) {
        this.name = name;
        this.cantEatMenus = cantEatMenus.stream()
                .map(Menu::from)
                .toList();
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
