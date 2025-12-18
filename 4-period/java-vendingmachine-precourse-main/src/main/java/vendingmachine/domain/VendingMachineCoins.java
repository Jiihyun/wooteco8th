package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.EnumMap;
import java.util.HashMap;
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

    public Map<Coin, Integer> calculateChanges(int userMoney) {
        Map<Coin, Integer> changes = new HashMap<>();
        for (Map.Entry<Coin, Integer> entry : coins.entrySet()) {
            userMoney = putChanges(userMoney, entry, changes);
        }
        return changes;
    }

    private int putChanges(int userMoney, Map.Entry<Coin, Integer> entry, Map<Coin, Integer> changes) {
        for (int quantity = 0; quantity < entry.getValue(); quantity++) {
            int amount = entry.getKey().getAmount();
            if (userMoney >= amount) {
                userMoney -= amount;
                changes.put(entry.getKey(), changes.getOrDefault(entry.getKey(), 0) + 1);
            }
        }
        return userMoney;
    }

    public Map<Coin, Integer> getCoins() {
        return coins;
    }
}
