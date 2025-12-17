package store.domain;

public class Product {

    private final String name;
    private final int price;
    private final Promotion promotion;
    private final Inventory inventory;

    public static Product of(String name, int price, Promotion promotion, int quantity) {
        if (promotion.exists()) {
            return new Product(name, price, promotion, new Inventory(quantity, 0));
        }
        return new Product(name, price, promotion, new Inventory(0, quantity));
    }

    public Product(String name, int price, Promotion promotion, Inventory inventory) {
        this.name = name;
        this.price = price;
        this.promotion = promotion;
        this.inventory = inventory;
    }

    public void addQuantityByPromotionType(int quantity, Promotion promotion) {
        if (promotion.exists()) {
            inventory.addPromotionQuantity(quantity);
            return;
        }
        inventory.addNonPromotionQuantity(quantity);
    }

    public boolean hasPromotion() {
        return promotion.exists();
    }

    public void minusNonPromotionQuantity(int purchasedQuantity) {
        inventory.minusNonPromotionQuantity(purchasedQuantity);
    }

    public boolean hasSufficientPromotionQuantity(int purchasedQuantity) {
        return inventory.hasSufficientPromotionQuantity(purchasedQuantity);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getPromotionQuantity() {
        return inventory.getPromotionQuantity();
    }

    public int getPromotionBuyQuantity() {
        return promotion.getBuyQuantity();
    }

    public int getPromotionGetQuantity() {
        return promotion.getGetQuantity();
    }
}
