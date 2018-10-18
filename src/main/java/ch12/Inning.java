package ch12;

/**
 * @author yuzhe
 * @since 8/27/18
 */
public abstract class Inning {

    public Inning() throws BaseballException {
    }

    public void event() throws BaseballException {
        //nothing
        System.out.println("Inning.event()");
    }

    public abstract void atBat() throws Strike, Foul;

    public void walk() {
        //throw runtime exception
    }

}
