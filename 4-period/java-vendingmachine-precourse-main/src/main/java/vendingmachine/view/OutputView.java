package vendingmachine.view;

import vendingmachine.domain.VendingMachineCoins;

public final class OutputView {

    private static final String VENDINGMACHINE_COINS_FORMAT = "%d원 - %d개";

    private OutputView() {
    }

    public static void showVendingMachineCoins(VendingMachineCoins vendingMachineCoins) {
        System.out.println("자판기가 보유한 동전");
        vendingMachineCoins.getCoins().forEach(
                (coin, quantity) ->
                        System.out.println(VENDINGMACHINE_COINS_FORMAT.formatted(coin.getAmount(), quantity))
        );
    }
}
