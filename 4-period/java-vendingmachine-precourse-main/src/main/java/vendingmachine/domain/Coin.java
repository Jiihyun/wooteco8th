package vendingmachine.domain;

import java.util.Arrays;
import java.util.List;
import vendingmachine.exception.ExceptionMessage;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public static List<Integer> getAmounts() {
        return Arrays.stream(values())
                .map(coin -> coin.amount)
                .toList();
    }

    public static Coin from(int amount) {
        return Arrays.stream(Coin.values())
                .filter(coin -> coin.amount == amount)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.INVALID_COIN.getMessage()));
    }

    public int getAmount() {
        return amount;
    }
}
