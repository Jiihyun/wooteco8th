package store.util;

import java.util.function.Supplier;
import store.view.OutputView;

public final class RetryHandler {

    private RetryHandler() {
    }

    public static <T> T retryOnInvalidInput(Supplier<T> input) {
        while (true) {
            try {
                return input.get();
            } catch (IllegalArgumentException e) {
                OutputView.showError(e.getMessage());
            }
        }
    }
}
