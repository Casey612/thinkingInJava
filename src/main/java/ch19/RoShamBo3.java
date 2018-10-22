package ch19;

/**
 * @author yuzhe
 * @since 10/22/18
 */
public enum  RoShamBo3 implements Competitor<RoShamBo3>{
    PAPER{
        @Override
        public Outcome compete(RoShamBo3 competitor) {
            return null;
        }
    },
    SCISSORS{
        @Override
        public Outcome compete(RoShamBo3 competitor) {
            return null;
        }
    }, ROCK{
        @Override
        public Outcome compete(RoShamBo3 competitor) {
            return null;
        }
    };


    @Override
    public abstract Outcome compete(RoShamBo3 competitor);
}
