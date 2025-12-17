package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import vendingmachine.dto.PurchaseItem;
import vendingmachine.exception.ExceptionMessage;
import vendingmachine.util.Parser;

public final class InputView {

    private static final String PURCHASE_ITEMS_DELIMITER = ";";
    private static final Pattern PURCHASE_ITEM_FORMAT = Pattern.compile("^\\[([가-힣]+),(\\d+),(\\d+)\\]*$");

    private InputView() {
    }

    public static int readVendingMachineMoney() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        String input = readLine();
        return Parser.parseToInt(input);
    }

    public static List<PurchaseItem> readPurchaseItem() {
        System.out.println("상품명과 가격, 수량을 입력해 주세요.");
        String input = readLine();
        List<String> purchaseItems = Parser.parseByDelimiter(input, PURCHASE_ITEMS_DELIMITER);
        return purchaseItems.stream()
                .map(InputView::createPurchaseItem)
                .toList();
    }

    private static PurchaseItem createPurchaseItem(String purchaseItem) {
        Matcher matcher = PURCHASE_ITEM_FORMAT.matcher(purchaseItem);
        validatePurchaseItemFormat(matcher);

        String name = matcher.group(1);
        int price = Parser.parseToInt(matcher.group(2));
        int quantity = Parser.parseToInt(matcher.group(3));
        return new PurchaseItem(name, price, quantity);
    }

    private static void validatePurchaseItemFormat(Matcher matcher) {
        if (!matcher.matches()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_FORMAT.getMessage());
        }
    }

    public static int readUserMoney() {
        System.out.println("투입 금액을 입력해 주세요.");
        String input = readLine();
        return Parser.parseToInt(input);
    }

    public static String readPurchaseProduct(int userMoney) {
        System.out.println("투입 금액: %d원".formatted(userMoney));
        System.out.println("구매할 상품명을 입력해 주세요.");
        return readLine();
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
