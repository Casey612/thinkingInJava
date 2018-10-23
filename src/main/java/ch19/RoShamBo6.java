package ch19;

/**
 * @author yuzhe
 * @since 10/23/18
 */
public enum RoShamBo6 implements Competitor<RoShamBo6> {
    PAPER, SCISSORS, ROCK;

    private static Outcome[][] table = {
            {Outcome.DRAW, Outcome.LOSE, Outcome.WIN},
            {Outcome.WIN, Outcome.DRAW, Outcome.LOSE},
            {Outcome.LOSE, Outcome.WIN, Outcome.DRAW},
    };

    @Override
    public Outcome compete(RoShamBo6 competitor) {
        return table[this.ordinal()][competitor.ordinal()];
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo6.class, 20);
    }
}
