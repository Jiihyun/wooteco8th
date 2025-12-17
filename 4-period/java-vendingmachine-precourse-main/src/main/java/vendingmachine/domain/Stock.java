package vendingmachine.domain;

import java.util.HashMap;
import java.util.Map;

public class Stock {

    private final Map<Product, Integer> stock = new HashMap<>();

    public Stock() {
    }

    public void put(Product product, int quantity) {
        //TODO: 동일한 상품 검증 추가
        stock.put(product, quantity);
    }

    public Map<Product, Integer> getStock() {
        return stock;
    }
}
