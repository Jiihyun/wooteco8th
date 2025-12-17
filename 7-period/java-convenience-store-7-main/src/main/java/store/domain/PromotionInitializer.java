package store.domain;

import java.time.LocalDate;
import java.util.List;
import store.FileReader;
import store.exception.ExceptionMessage;
import store.util.Parser;

public class PromotionInitializer {

    private static final String DELIMITER = ",";

    public Promotions initialize() {
        Promotions promotions = new Promotions();
        for (String promotion : FileReader.readPromotions()) {
            addPromotionFromLine(promotion, promotions);
        }
        return promotions;
    }

    private void addPromotionFromLine(String promotion, Promotions promotions) {
        List<String> promotionInfo = Parser.parseByDelimiter(promotion, DELIMITER);

        String name = promotionInfo.get(0);
        String buy = promotionInfo.get(1);
        String get = promotionInfo.get(2);
        String startDate = promotionInfo.get(3);
        String endDate = promotionInfo.get(4);

        promotions.add(createPromotion(name, buy, get, startDate, endDate));
    }

    private Promotion createPromotion(String name, String buyQuantity, String getQuantity,
                                      String startDate, String endDate) {
        try {
            return new Promotion(name,
                    Integer.parseInt(buyQuantity), Integer.parseInt(getQuantity),
                    LocalDate.parse(startDate), LocalDate.parse(endDate),
                    Promotion.PromotionType.EXISTENT);
        } catch (RuntimeException runtimeException) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_FILE_CONTENT.getMessage());
        }
    }
}
