package pairmatching.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import pairmatching.exception.ExceptionMessage;

class PairTest {

    @Test
    void 순서가_달라도_같은_페어이다() {
        Pair p1 = new Pair(List.of(new Crew("a"), new Crew("b")));
        Pair p2 = new Pair(List.of(new Crew("b"), new Crew("a")));

        assertThat(p1).isEqualTo(p2);
    }

    @Test
    void 중복된_크루로_페어_매칭시_예외가_발생한다() {
        assertThatThrownBy(() -> new Pair(List.of(new Crew("a"), new Crew("a"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.DUPLICATED_CREW.getMessage());
    }
}
