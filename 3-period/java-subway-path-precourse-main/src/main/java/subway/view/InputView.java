package subway.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Scanner;
import subway.exception.ExceptionMessage;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readStartStation() {
        System.out.println("## 출발역을 입력하세요.");
        return readLine();
    }

    public String readEndStation() {
        System.out.println("## 도착역을 입력하세요.");
        return readLine();
    }

    private String readLine() {
        String input = Console.readLine().strip();
        validateInput(input);
        return input;
    }

    private void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.INPUT_BLANK.getMessage());
        }
    }
}
