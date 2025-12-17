package store.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Products {

    private final Map<String, Product> products = new LinkedHashMap<>();

    public void put(Product product) {
        products.put(product.getName(), product);
    }

    public Optional<Product> findByName(String name) {
        return Optional.ofNullable(products.get(name));
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }
}
