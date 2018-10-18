package ch12;

/**
 * @author yuzhe
 * @since 8/27/18
 */
public class StormyInning extends Inning implements Storm {

    public StormyInning() throws BaseballException {
        super();
    }

    @Override
    public void atBat() throws PopFoul{
        //extents inning
    }

    @Override
    public void event() {
        System.out.println("StormyInning.event()");
    }

    @Override
    public void rainHard() throws RainedOut {
        //implement storm
    }

    public static void main(String[] args) throws BaseballException {
        Inning inning = new StormyInning();
        inning.event();
    }
}
