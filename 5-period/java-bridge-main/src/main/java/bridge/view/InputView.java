package bridge.view;

import bridge.domain.MovingCommand;
import bridge.domain.RetryCommand;
import bridge.exception.ExceptionMessage;
import bridge.util.Parser;
import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public final class InputView {

    private static final String NEW_LINE = System.lineSeparator();

    private InputView() {
    }

    /**
     * 다리의 길이를 입력받는다.
     */
    public static int readBridgeSize() {
        System.out.println("""
                다리 건너기 게임을 시작합니다.
                
                다리의 길이를 입력해주세요.""");
        return Parser.parseToInt(readLine());
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public MovingCommand readMoving() {
        System.out.println(NEW_LINE + "이동할 칸을 선택해주세요. (위: U, 아래: D)");
        return MovingCommand.from(readLine());
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public RetryCommand readGameCommand() {
        System.out.println(NEW_LINE + "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
        return RetryCommand.from(readLine());
    }

    private static String readLine() {
        String input = Console.readLine().strip();
        validateInput(input);
        return input;
    }

    private static void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.INPUT_BLANK.getMessage());
        }
    }
}
