package store;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

public class IntegrationTest extends NsTest {
    @Test
    void 단일_상품_구매_프로모션_없음_멤버십_없음() {
        assertSimpleTest(() -> {
            run("[물-3]", "N", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-0",
                    "내실돈1,500"
            );
        });
    }

    @Test
    void 단일_상품_구매_프로모션_원플러스원_수량_추가_불필요_멤버십_없음() {
        assertSimpleTest(() -> {
            run("[초코바-4]", "N", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-2,400",
                    "내실돈2,400"
            );
        });
    }

    @Test
    void 단일_상품_구매_프로모션_원플러스원_수량_추가_필요_추가_함_멤버십_없음() {
        assertSimpleTest(() -> {
            run("[초코바-3]", "Y", "N", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-2,400",
                    "내실돈2,400"
            );
        });
    }

    @Test
    void 단일_상품_구매_프로모션_원플러스원_수량_추가_필요_추가_안함_멤버십_없음() {
        assertSimpleTest(() -> {
            run("[초코바-3]", "N", "N", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-1,200",
                    "내실돈2,400"
            );
        });
    }

    @Test
    void 단일_상품_구매_프로모션_원플러스원_정가_결제_필요_결제_함_멤버십_없음() {
        assertSimpleTest(() -> {
            run("[초코바-7]", "Y", "N", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-2,400",
                    "내실돈6,000"
            );
        });
    }

    @Test
    void 단일_상품_구매_프로모션_원플러스원_정가_결제_필요_결제_안함_멤버십_없음() {
        assertSimpleTest(() -> {
            run("[초코바-7]", "N", "N", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-2,400",
                    "내실돈2,400"
            );
        });
    }

    @Test
    void 단일_상품_구매_프로모션_투플러스원_수량_추가_불필요_멤버십_없음() {
        assertSimpleTest(() -> {
            run("[콜라-6]", "N", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-2,000",
                    "내실돈4,000"
            );
        });
    }


    @Test
    void 단일_상품_구매_프로모션_투플러스원_수량_추가_필요_추가_함_멤버십_없음() {
        assertSimpleTest(() -> {
            run("[콜라-5]", "Y", "N", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-2,000",
                    "내실돈4,000"
            );
        });
    }

    @Test
    void 단일_상품_구매_프로모션_투플러스원_수량_추가_필요_추가_안함_멤버십_없음() {
        assertSimpleTest(() -> {
            run("[콜라-5]", "N", "N", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-1,000",
                    "내실돈4,000"
            );
        });
    }

    @Test
    void 단일_상품_구매_프로모션_투플러스원_정가_결제_필요_결제_함_멤버십_없음() {
        assertSimpleTest(() -> {
            run("[콜라-11]", "Y", "N", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-3,000",
                    "내실돈8,000"
            );
        });
    }

    @Test
    void 단일_상품_구매_프로모션_플러스원_정가_결제_필요_결제_안함_멤버십_없음() {
        assertSimpleTest(() -> {
            run("[콜라-11]", "N", "N", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-3,000",
                    "내실돈6,000"
            );
        });
    }

    @Test
    void 단일_상품_구매_프로모션_없음_멤버십_있음() {
        assertSimpleTest(() -> {
            run("[물-3]", "Y", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-0",
                    "멤버십할인-450",
                    "내실돈1,050"
            );
        });
    }

    @Test
    void 단일_상품_구매_프로모션_원플러스원_수량_추가_불필요_멤버십_있음() {
        assertSimpleTest(() -> {
            run("[초코바-4]", "Y", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-2,400",
                    "멤버십할인-0",
                    "내실돈2,400"
            );
        });
    }

    @Test
    void 단일_상품_구매_프로모션_원플러스원_수량_추가_필요_추가_함_멤버십_있음() {
        assertSimpleTest(() -> {
            run("[초코바-3]", "Y", "Y", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-2,400",
                    "멤버십할인-0",
                    "내실돈2,400"
            );
        });
    }

    @Test
    void 단일_상품_구매_프로모션_원플러스원_수량_추가_필요_추가_안함_멤버십_있음() {
        assertSimpleTest(() -> {
            run("[초코바-3]", "N", "Y", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-1,200",
                    "멤버십할인-360",
                    "내실돈2,040"
            );
        });
    }

    @Test
    void 단일_상품_구매_프로모션_원플러스원_정가_결제_필요_결제_함_멤버십_있음() {
        assertSimpleTest(() -> {
            run("[초코바-7]", "Y", "Y", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-2,400",
                    "멤버십할인-1,080",
                    "내실돈4,920"
            );
        });
    }

    @Test
    void 단일_상품_구매_프로모션_원플러스원_정가_결제_필요_결제_안함_멤버십_있음() {
        assertSimpleTest(() -> {
            run("[초코바-7]", "N", "Y", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-2,400",
                    "멤버십할인-0",
                    "내실돈2,400"
            );
        });
    }

    @Test
    void 단일_상품_구매_프로모션_투플러스원_수량_추가_불필요_멤버십_있음() {
        assertSimpleTest(() -> {
            run("[콜라-6]", "Y", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-2,000",
                    "멤버십할인-0",
                    "내실돈4,000"
            );
        });
    }


    @Test
    void 단일_상품_구매_프로모션_투플러스원_수량_추가_필요_추가_함_멤버십_있음() {
        assertSimpleTest(() -> {
            run("[콜라-5]", "Y", "Y", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-2,000",
                    "멤버십할인-0",
                    "내실돈4,000"
            );
        });
    }

    @Test
    void 단일_상품_구매_프로모션_투플러스원_수량_추가_필요_추가_안함_멤버십_있음() {
        assertSimpleTest(() -> {
            run("[콜라-5]", "N", "Y", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-1,000",
                    "멤버십할인-600",
                    "내실돈3,400"
            );
        });
    }

    @Test
    void 단일_상품_구매_프로모션_투플러스원_정가_결제_필요_결제_함_멤버십_있음() {
        assertSimpleTest(() -> {
            run("[콜라-11]", "Y", "Y", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-3,000",
                    "멤버십할인-600",
                    "내실돈7,400"
            );
        });
    }

    @Test
    void 단일_상품_구매_프로모션_플러스원_정가_결제_필요_결제_안함_멤버십_있음() {
        assertSimpleTest(() -> {
            run("[콜라-11]", "N", "Y", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-3,000",
                    "멤버십할인-0",
                    "내실돈6,000"
            );
        });
    }

    @Test
    void 여러_개의_상품_구매_프로모션_포함_멤버십_있음() {
        assertSimpleTest(() -> {
            run("[콜라-5],[사이다-2],[비타민워터-2],[오렌지주스-1]", "N", "Y", "N", "Y", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-2,000",
                    "멤버십할인-2,040",
                    "내실돈8,760"
            );
        });
    }

    @Test
    void 여러_개의_상품_구매_프로모션_포함_멤버십_있음_멤버십_최대_8000() {
        assertSimpleTest(() -> {
            run("[콜라-20],[사이다-15],[정식도시락-8]", "Y", "Y", "Y", "N");
            assertThat(output().replaceAll("\\s", "")).contains(
                    "행사할인-5,000",
                    "멤버십할인-8,000",
                    "내실돈73,200"
            );
        });
    }

    @Test
    void 예외_테스트_존재하지_않는_상품() {
        assertSimpleTest(() -> {
            runException("[존재하지않는상품-3]", "N", "N");
            assertThat(output()).contains("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 예외_테스트_올바르지_않은_형식() {
        assertSimpleTest(() -> {
            runException("abcd", "N", "N");
            assertThat(output()).contains("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
