package store.domain;

import java.util.List;
import java.util.Optional;
import store.FileReader;
import store.util.Parser;

public class ProductInitializer {

    private static final String DELIMITER = ",";

    private final Promotions promotions;

    public ProductInitializer(Promotions promotions) {
        this.promotions = promotions;
    }

    public Products initialize() {
        Products products = new Products();
        for (String productLine : FileReader.readProducts()) {
            addProductFromLine(products, productLine);
        }
        return products;
    }

    private void addProductFromLine(Products products, String productLine) {
        List<String> productInfo = Parser.parseByDelimiter(productLine, DELIMITER);

        String name = productInfo.get(0);
        int price = Integer.parseInt(productInfo.get(1));
        int quantity = Integer.parseInt(productInfo.get(2));
        Promotion promotion = promotions.findByName(productInfo.get(3));

        add(products, name, quantity, promotion, price);
    }

    private void add(Products products, String name, int quantity, Promotion promotion, int price) {
        Optional<Product> productOptional = products.findByName(name);
        if (productOptional.isPresent()) {
            productOptional.get().addQuantityByPromotionType(quantity, promotion);
            return;
        }
        products.put(Product.of(name, price, promotion, quantity));
    }
}
