package thinkingInJava.ch12;

/**
 * @author yuzhe
 * @since 8/27/18
 */
public interface Storm {

    public void event() throws RainedOut;

    public void rainHard() throws RainedOut;

}
