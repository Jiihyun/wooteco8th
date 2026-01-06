package subway.view;

import java.util.Scanner;
import subway.domain.command.MainCommand;
import subway.domain.command.SearchCommand;
import subway.exception.ExceptionMessage;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public MainCommand readMainCommand() {
        System.out.println("""
                
                ## 메인 화면
                1. 경로 조회
                Q. 종료
                
                ## 원하는 기능을 선택하세요.""");
        return MainCommand.from(readLine());
    }

    public SearchCommand readSearchCommand() {
        System.out.println("""
                
                ## 경로 기준
                1. 최단 거리
                2. 최소 시간
                B. 돌아가기
                
                ## 원하는 기능을 선택하세요.""");
        return SearchCommand.from(readLine());
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
        String input = scanner.nextLine().strip();
        validateInput(input);
        return input;
    }

    private void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.INPUT_BLANK.getMessage());
        }
    }
}
