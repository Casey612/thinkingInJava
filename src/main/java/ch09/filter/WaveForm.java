package ch09.filter;

/**
 * @author yuzhe
 * @since 8/14/18
 */
public class WaveForm {

    private static long counter;
    private final long id = counter++;

    @Override
    public String toString(){
        return "WaveForm " + id;
    }

}
