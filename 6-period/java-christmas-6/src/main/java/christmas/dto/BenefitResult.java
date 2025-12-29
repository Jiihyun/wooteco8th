package christmas.dto;

import java.util.Map;

public record BenefitResult(
        Map<String, Integer> benefit,
        int benefitAmount,
        int payAmountAfterDiscount
) {
}
