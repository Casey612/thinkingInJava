package ch19;

/**
 * @author yuzhe
 * @since 10/23/18
 */
public enum RoShamBo4 implements Competitor<RoShamBo4> {
    ROCK{
        @Override
        public Outcome compete(RoShamBo4 competitor) {
            return compete(RoShamBo4.SCISSORS, competitor);
        }
    },
    SCISSORS{
        @Override
        public Outcome compete(RoShamBo4 competitor) {
            return compete(RoShamBo4.PAPER, competitor);
        }
    },
    PAPER{
        @Override
        public Outcome compete(RoShamBo4 competitor) {
            return compete(RoShamBo4.ROCK, competitor);
        }
    };

    Outcome compete(RoShamBo4 loser, RoShamBo4 oppoent) {
        return (oppoent == this) ? Outcome.DRAW : ((oppoent == loser) ? Outcome.LOSE : Outcome.WIN);
    }

    @Override
    public abstract Outcome compete(RoShamBo4 competitor);

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo4.class, 20);
    }

}
