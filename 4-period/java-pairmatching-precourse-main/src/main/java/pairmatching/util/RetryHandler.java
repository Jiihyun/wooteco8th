package pairmatching.util;

import java.util.function.Supplier;
import pairmatching.view.OutputView;

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

    public static void retryOnInvalidInput(Runnable input) {
        while (true) {
            try {
                input.run();
                return;
            } catch (IllegalArgumentException e) {
                OutputView.showError(e.getMessage());
            }
        }
    }
}
