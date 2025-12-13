package lotto.view;

import java.util.List;
import lotto.domain.Rank;

public class RankFormatter {

    private RankFormatter() {
    }

    public static List<Rank> orderRanks() {
        return List.of(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST);
    }

    public static String formatRank(Rank rank, int count) {
        String text = "%d개 일치".formatted(rank.getMatchingCount());
        if (rank == Rank.SECOND) {
            text += ", 보너스 볼 일치";
        }
        return "%s (%,d원) - %d개".formatted(text, rank.getPrizeAmount(), count);
    }
}
