package store.domain;

import java.util.ArrayList;
import java.util.List;

public class Promotions {

    private final List<Promotion> promotions;

    public Promotions() {
        this.promotions = new ArrayList<>();
    }

    public void add(Promotion promotion) {
        promotions.add(promotion);
    }

    public Promotion findByName(String promotionName) {
        return promotions.stream()
                .filter(promotion -> promotion.hasSameName(promotionName))
                .findAny()
                .orElse(Promotion.none());
    }
}
