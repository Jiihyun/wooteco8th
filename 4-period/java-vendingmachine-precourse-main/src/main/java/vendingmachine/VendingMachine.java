package vendingmachine;

import java.util.List;
import java.util.Map;
import vendingmachine.domain.Coin;
import vendingmachine.domain.Customer;
import vendingmachine.domain.Product;
import vendingmachine.domain.Stock;
import vendingmachine.domain.VendingMachineCoins;
import vendingmachine.dto.PurchaseItem;
import vendingmachine.util.RetryHandler;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachine {

    public void run() {
        VendingMachineCoins vendingMachineCoins = RetryHandler.retryOnInvalidInput(this::getVendingMachineCoins);
        Stock stock = RetryHandler.retryOnInvalidInput(this::initStock);
        int userMoney = purchase(stock);
        Map<Coin, Integer> changes = vendingMachineCoins.calculateChanges(userMoney);
        OutputView.showResult(userMoney, changes);
    }

    private VendingMachineCoins getVendingMachineCoins() {
        int vendingMachineMoney = InputView.readVendingMachineMoney();
        VendingMachineCoins vendingMachineCoins = new VendingMachineCoins(vendingMachineMoney);
        OutputView.showVendingMachineCoins(vendingMachineCoins);
        return vendingMachineCoins;
    }

    private Stock initStock() {
        List<PurchaseItem> purchaseItems = InputView.readPurchaseItem();
        Stock stock = new Stock();
        for (PurchaseItem purchaseItem : purchaseItems) {
            Product product = new Product(purchaseItem.name(), purchaseItem.price());
            stock.put(product, purchaseItem.quantity());
        }
        return stock;
    }

    private int purchase(Stock stock) {
        int userMoney = RetryHandler.retryOnInvalidInput(InputView::readUserMoney);
        Customer customer = new Customer(userMoney);

        while (canPurchase(stock, userMoney)) {
            int finalUserMoney = userMoney;
            Product product = RetryHandler.retryOnInvalidInput(() -> pickProduct(stock, finalUserMoney));
            userMoney = customer.purchase(product);
            stock.deductStock(product);
        }
        return userMoney;
    }

    private boolean canPurchase(Stock stock, int userMoney) {
        return !stock.isSoldOut()
                && userMoney >= stock.findMinPrice();
    }

    private Product pickProduct(Stock stock, int userMoney) {
        String purchaseProduct = InputView.readPurchaseProduct(userMoney);
        return stock.findProduct(purchaseProduct);
    }
}
