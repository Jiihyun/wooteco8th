package store;

import store.controller.StoreController;
import store.domain.ProductInitializer;
import store.domain.Products;
import store.domain.PromotionInitializer;
import store.domain.Promotions;

public class Application {
    public static void main(String[] args) {
        PromotionInitializer promotionInitializer = new PromotionInitializer();
        Promotions promotions = promotionInitializer.initialize();

        ProductInitializer productInitializer = new ProductInitializer(promotions);
        Products products = productInitializer.initialize();

        StoreController storeController = new StoreController();
        storeController.run(products);
    }
}
