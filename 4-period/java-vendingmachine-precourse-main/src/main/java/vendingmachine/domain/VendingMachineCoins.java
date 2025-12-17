package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.EnumMap;
import java.util.Map;
import vendingmachine.exception.ExceptionMessage;

public class VendingMachineCoins {

    private static final int MIN_COIN_RANGE = 100;
    private static final int MAX_COIN_RANGE = 100_000;

    private final Map<Coin, Integer> coins;

    public VendingMachineCoins(int vendingMachineMoney) {
        validateMoney(vendingMachineMoney);
        this.coins = initCoins(vendingMachineMoney);
    }

    private void validateMoney(int vendingMachineMoney) {
        if (isOutOfRange(vendingMachineMoney)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_VENDING_MACHINE_MONEY.getMessage());
        }
        if (vendingMachineMoney % Coin.COIN_10.getAmount() != 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_VENDING_MACHINE_MONEY.getMessage());
        }
    }

    private boolean isOutOfRange(Integer number) {
        return number < MIN_COIN_RANGE || number > MAX_COIN_RANGE;
    }

    private Map<Coin, Integer> initCoins(int vendingMachineMoney) {
        Map<Coin, Integer> coins = new EnumMap<>(Coin.class);
        for (Coin coin : Coin.values()) {
            coins.put(coin, 0);
        }
        put(vendingMachineMoney, coins);
        return coins;
    }

    private void put(int vendingMachineMoney, Map<Coin, Integer> coins) {
        while (vendingMachineMoney > 0) {
            int amount = Randoms.pickNumberInList(Coin.getAmounts());
            if (amount > vendingMachineMoney) {
                continue;
            }
            Coin coin = Coin.from(amount);
            coins.put(coin, coins.get(coin) + 1);
            vendingMachineMoney -= coin.getAmount();
        }
    }

    public void deduct(int userMoney) {
        //FIX: 로직 수정
        while (userMoney > 0) {
            for (Map.Entry<Coin, Integer> entry : coins.entrySet()) {
                userMoney -= (entry.getKey().getAmount() * entry.getValue());
            }
        }
    }

    public Map<Coin, Integer> getCoins() {
        return coins;
    }
}
