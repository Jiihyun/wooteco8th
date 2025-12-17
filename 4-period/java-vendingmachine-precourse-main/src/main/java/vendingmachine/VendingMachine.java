package vendingmachine;

import vendingmachine.domain.VendingMachineCoins;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachine {

    public void run() {
        int vendingMachineMoney = InputView.readVendingMachineMoney();
        VendingMachineCoins vendingMachineCoins = new VendingMachineCoins(vendingMachineMoney);
        OutputView.showVendingMachineCoins(vendingMachineCoins);
    }
}
