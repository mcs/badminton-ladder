package ladder.util;

import ladder.model.Player;
import net.sourceforge.stripes.util.Log;
import org.springframework.util.Assert;

public class PyramidUtil {

    private static final Log log = Log.getInstance(PyramidUtil.class);

    private PyramidUtil() {
        // static utility class
    }

    /**
     * Gets the row in the pyramid for a given rank.
     * <p>
     * Algorithm: <pre>row := CEIL(1/2 * (SQRT(8 * rank + 1) - 1))</pre>
     * <br>
     * <tt>rank</tt> must be a positive integer.
     *
     * @param rank the rank for which the pyramid's row is looked for
     * @return the row of the rank
     * @throws IllegalArgumentException if rank &lt;= 0
     */
    public static int getRowForRank(int rank) throws IllegalArgumentException {
        Assert.isTrue(rank > 0);
        double row = 8 * rank + 1;
        row = Math.sqrt(row) - 1;
        row /= 2;
        row = Math.ceil(row);

        return (int) Math.round(row);
    }

    /**
     * Gets the maximum rank in the pyramid for a given row.
     * <p>
     * Algorithm: <pre>rank := row * (row + 1) / 2</pre>
     * <br>
     * <tt>rank</tt> must be a positive integer.
     *
     * @param rank the rank for which the pyramid's row is looked for
     * @return the row of the rank
     * @throws IllegalArgumentException if rank &lt;= 0
     */
    public static int getMaxRankForRow(int row) {
        Assert.isTrue(row > 0);
        // Algorithm: maxRank := row * (row + 1) / 2
        return row * (row + 1) / 2;
    }

    public static boolean isChallengeAllowed(Player challenger, Player challenged) {
        if (!challenger.getLadder().equals(challenged.getLadder())) {
            log.warn("Players not in same ladder (" + challenger + ", " + challenged + ")");
            return false;
        }
        // logic
        int rankChallenger = challenger.getLadder().getRank(challenger);
        int rankChallenged = challenged.getLadder().getRank(challenged);
        if (rankChallenged >= rankChallenger) {
            return false;
        }

        int rowChallenger = PyramidUtil.getRowForRank(rankChallenger);
        return rankChallenger - rowChallenger < rankChallenged;
    }
}
