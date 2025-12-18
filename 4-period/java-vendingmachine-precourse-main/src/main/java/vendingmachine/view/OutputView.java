package vendingmachine.view;

import java.util.Map;
import vendingmachine.domain.Coin;
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

    public static void showResult(int userMoney, Map<Coin, Integer> changes) {
        System.out.println("투입 금액: %d원".formatted(userMoney));
        System.out.println("잔돈");
        for (Map.Entry<Coin, Integer> entry : changes.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.println(VENDINGMACHINE_COINS_FORMAT.formatted(entry.getKey().getAmount(), entry.getValue()));
            }
        }
    }

    public static void showError(String message) {
        System.out.println(message);
    }
}
