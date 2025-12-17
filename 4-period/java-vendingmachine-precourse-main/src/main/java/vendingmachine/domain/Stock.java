package vendingmachine.domain;

import java.util.HashMap;
import java.util.Map;
import vendingmachine.exception.ExceptionMessage;

public class Stock {

    private final Map<Product, Integer> stock = new HashMap<>();

    public Stock() {
    }

    public void put(Product product, int quantity) {
        //TODO: 동일한 상품 검증 추가
        stock.put(product, quantity);
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

    public Map<Product, Integer> getStock() {
        return stock;
    }
}
