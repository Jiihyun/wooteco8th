package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import pairmatching.domain.Command;
import pairmatching.domain.RematchCommand;
import pairmatching.dto.PairInfoRequest;
import pairmatching.exception.ExceptionMessage;
import pairmatching.util.Parser;

public final class InputView {

    private static final String READ_COMMAND_MESSAGE = """
            
            기능을 선택하세요.
            1. 페어 매칭
            2. 페어 조회
            3. 페어 초기화
            Q. 종료""";
    private static final String READ_PAIR_INFO_MESSAGE = """
            
            #############################################
            과정: 백엔드 | 프론트엔드
            미션:
              - 레벨1: 자동차경주 | 로또 | 숫자야구게임
              - 레벨2: 장바구니 | 결제 | 지하철노선도
              - 레벨3:\s
              - 레벨4: 성능개선 | 배포
              - 레벨5:\s
            ############################################
            과정, 레벨, 미션을 선택하세요.
            ex) 백엔드, 레벨1, 자동차경주""";
    private static final String INFO_DELIMITER = ", ";

    private InputView() {
    }

    public static Command readCommand() {
        System.out.println(READ_COMMAND_MESSAGE);
        return Command.from(readLine());
    }

    public static PairInfoRequest readPairInfo() {
        System.out.println(READ_PAIR_INFO_MESSAGE);
        List<String> token = Parser.parseByDelimiter(readLine(), INFO_DELIMITER);
        return PairInfoRequest.of(token.getFirst(), token.get(1), token.getLast());
    }

    public static RematchCommand readRematch() {
        System.out.println("""
                매칭 정보가 있습니다. 다시 매칭하시겠습니까?
                네 | 아니오""");
        return RematchCommand.from(readLine());
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
