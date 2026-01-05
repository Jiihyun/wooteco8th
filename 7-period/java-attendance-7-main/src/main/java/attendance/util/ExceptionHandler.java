package attendance.util;

import attendance.view.OutputView;
import java.util.function.Supplier;


public final class ExceptionHandler {

    private ExceptionHandler() {
    }

    public static <T> T showError(Supplier<T> input) {
        try {
            return input.get();
        } catch (IllegalArgumentException e) {
            OutputView.showError(e.getMessage());
        }
        //FIX
        return input.get();
    }

    public static void showError(Runnable input) {
        try {
            input.run();
        } catch (IllegalArgumentException e) {
            OutputView.showError(e.getMessage());
        }
    }
}
