package store.dto.response;

import store.domain.Product;

public record FreeProductResult(
        String name,
        int totalQuantity,
        int freeProductQuantityByAuto,
        int freeProductQuantityByManual,
        int price
) {
    public static FreeProductResult from(Product product, int freeProductQuantityByAuto, int freeProductQuantityByManual) {
        return new FreeProductResult(
                product.getName(),
                freeProductQuantityByAuto + freeProductQuantityByManual,
                freeProductQuantityByAuto,
                freeProductQuantityByManual,
                product.getPrice() * (freeProductQuantityByAuto + freeProductQuantityByManual)
        );
    }
}
