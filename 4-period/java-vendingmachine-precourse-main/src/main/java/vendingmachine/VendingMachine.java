package vendingmachine;

import java.util.List;
import vendingmachine.domain.Customer;
import vendingmachine.domain.Product;
import vendingmachine.domain.Stock;
import vendingmachine.domain.VendingMachineCoins;
import vendingmachine.dto.PurchaseItem;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachine {

    public void run() {
        int vendingMachineMoney = InputView.readVendingMachineMoney();
        VendingMachineCoins vendingMachineCoins = new VendingMachineCoins(vendingMachineMoney);
        OutputView.showVendingMachineCoins(vendingMachineCoins);

        List<PurchaseItem> purchaseItems = InputView.readPurchaseItem();
        int userMoney = InputView.readUserMoney();
        Customer customer = new Customer(userMoney);

        Stock stock = new Stock();
        for (PurchaseItem purchaseItem : purchaseItems) {
            Product product = new Product(purchaseItem.name(), purchaseItem.price());
            stock.put(product, purchaseItem.quantity());
        }

        while (true) {
            String purchaseProduct = InputView.readPurchaseProduct(userMoney);
            Product product = stock.findProduct(purchaseProduct);
            stock.deductStock(product);
            customer.purchase(product);
            userMoney = customer.getUserMoney();
            if (stock.isSoldOut()
                    || userMoney >= stock.findMinPrice()) {
                break;
            }
        }
        vendingMachineCoins.deduct(userMoney);
        OutputView.showResult(userMoney, vendingMachineCoins);
    }
}
