package java.ch09.filter;

/**
 * @author yuzhe
 * @since 8/14/18
 */
public class Filter {

    public String name() {
        return getClass().getSimpleName();
    }

    public WaveForm process(WaveForm input){
        return input;
    }
}
