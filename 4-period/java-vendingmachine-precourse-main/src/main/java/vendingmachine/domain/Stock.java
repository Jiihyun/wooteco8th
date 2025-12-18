package vendingmachine.domain;

import java.util.HashMap;
import java.util.Map;
import vendingmachine.exception.ExceptionMessage;

public class Stock {

    private final Map<Product, Integer> stock = new HashMap<>();

    public Stock() {
    }

    public void put(Product product, int quantity) {
        if (hasSameProduct(product)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_PRODUCT.getMessage());
        }
        stock.put(product, quantity);
    }

    private boolean hasSameProduct(Product product) {
        return stock.keySet().stream()
                .anyMatch(stock -> stock.getName().equals(product.getName()));
    }

    public Product findProduct(String name) {
        return stock.keySet().stream()
                .filter(product -> product.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.PRODUCT_NOT_EXISTS.getMessage()));
    }

    public void deductStock(Product product) {
        stock.put(product, stock.get(product) - 1);
    }

    public boolean isSoldOut() {
        return stock.values().stream()
                .allMatch(quantity -> quantity <= 0);
    }

    public int findMinPrice() {
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Product, Integer> entry : stock.entrySet()) {
            min = Math.min(min, entry.getKey().getPrice());
        }
        return min;
    }
}
