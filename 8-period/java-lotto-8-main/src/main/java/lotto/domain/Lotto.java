package lotto.domain;

import java.util.List;
import lotto.exception.ExceptionMessage;

public class Lotto {

    public static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }

    private void validate(List<Integer> numbers) {
        validateLottoSize(numbers);
        validateUniqueNumber(numbers);
    }

    private static void validateLottoSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_LOTTO_SIZE.getMessage());
        }
    }

    private void validateUniqueNumber(List<Integer> numbers) {
        if (isDuplicated(numbers)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_LOTTO_NUMBER.getMessage());
        }
    }

    private boolean isDuplicated(final List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .count() != numbers.size();
    }
}
