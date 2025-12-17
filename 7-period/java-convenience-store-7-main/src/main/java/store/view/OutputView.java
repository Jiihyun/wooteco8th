package store.view;

import java.util.List;
import store.domain.Product;
import store.dto.response.FreeProductResult;
import store.dto.response.OrderProcessResult;
import store.dto.response.PurchasedProductResult;

public class OutputView {

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String PRODUCT_FORMAT = "- %s %,d원 %s %s";
    private static final String PURCHASED_PRODUCTS_FORMAT = "%s\t\t\t%d\t\t%,d";
    private static final String FREE_PRODUCT_FORMAT = "%s\t\t\t%d";
    private static final String TOTAL_PRICE_FORMAT = """
            =============================
            총구매액\t\t\t%d\t\t %,d
            행사할인\t\t\t\t\t-%,d
            멤버십할인\t\t\t\t\t-%,d
            내실돈\t\t\t\t\t %,d
            """;

    public static void showInventory(List<Product> products) {
        System.out.println("""
                안녕하세요. w편의점입니다.
                현재 보유하고 있는 상품입니다.
                """);
        products.forEach(OutputView::showProduct);
    }

    private static void showProduct(Product product) {
        if (product.hasPromotion()) {
            showPromotionProduct(product);
            showNonPromotionProduct(product);
            return;
        }
        showNonPromotionProduct(product);
    }

    private static void showNonPromotionProduct(Product product) {
        int quantity = product.getInventory().getNonPromotionQuantity();
        String quantityFormat = makeQuantityFormat(quantity);
        String nonPromotionFormat = "";
        System.out.println(PRODUCT_FORMAT.formatted(
                product.getName(),
                product.getPrice(),
                quantityFormat,
                nonPromotionFormat
        ));
    }

    private static void showPromotionProduct(Product product) {
        int quantity = product.getInventory().getPromotionQuantity();
        String quantityFormat = makeQuantityFormat(quantity);
        System.out.println(PRODUCT_FORMAT.formatted(
                product.getName(),
                product.getPrice(),
                quantityFormat,
                product.getPromotion().getName()
        ));
    }

    private static String makeQuantityFormat(int quantity) {
        if (quantity == 0) {
            return "재고 없음";
        }
        return quantity + "개";
    }

    public static void showReceipt(OrderProcessResult result, int membershipDiscount) {
        showPurchasedProducts(result.purchasedProducts());
        showFreeProducts(result.freeProducts());
        showTotalPrice(result, membershipDiscount);
    }

    private static void showPurchasedProducts(List<PurchasedProductResult> purchaseProducts) {
        System.out.println(NEW_LINE + "===========W 편의점=============");
        System.out.println("상품명\t\t\t수량\t\t금액");
        for (PurchasedProductResult purchasedProductResult : purchaseProducts) {
            System.out.println(PURCHASED_PRODUCTS_FORMAT.formatted(
                    purchasedProductResult.name(),
                    purchasedProductResult.quantity(),
                    purchasedProductResult.price()));
        }
    }

    private static void showFreeProducts(List<FreeProductResult> freeProducts) {
        System.out.println("===========증\t정============");
        for (FreeProductResult freeProductResult : freeProducts) {
            System.out.println(FREE_PRODUCT_FORMAT.formatted(
                    freeProductResult.name(),
                    freeProductResult.totalQuantity()
            ));
        }
    }

    private static void showTotalPrice(OrderProcessResult result, int membershipDiscount) {
        int totalQuantity = result.purchasedProducts().stream()
                .mapToInt(PurchasedProductResult::quantity)
                .sum();
        int totalPrice = result.purchasedProducts().stream()
                .mapToInt(PurchasedProductResult::price)
                .sum();
        int promotionDiscountAmount = result.freeProducts().stream()
                .mapToInt(FreeProductResult::price)
                .sum();
        showTotalPrice(membershipDiscount, totalQuantity, totalPrice, promotionDiscountAmount);
    }

    private static void showTotalPrice(int membershipDiscount, int totalQuantity, int totalPrice, int promotionDiscountAmount) {
        System.out.println(TOTAL_PRICE_FORMAT.formatted(
                totalQuantity, totalPrice,
                promotionDiscountAmount,
                membershipDiscount,
                totalPrice - promotionDiscountAmount - membershipDiscount
        ));
    }

    public static void showError(String message) {
        System.out.println(ERROR_MESSAGE_PREFIX + message);
    }
}
