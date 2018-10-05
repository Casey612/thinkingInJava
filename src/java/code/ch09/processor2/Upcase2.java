package code.ch09.processor2;

/**
 * @author yuzhe
 * @since 8/15/18
 */
public class Upcase2 extends StringProcessor {

    @Override
    public String process(Object input){
        return ((String) input).toUpperCase();
    }

}
