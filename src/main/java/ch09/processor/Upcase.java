package java.ch09.processor;

/**
 * @author yuzhe
 * @since 8/14/18
 */
public class Upcase extends Processor{

    @Override
    String process(Object input){
        return ((String) input).toUpperCase();
    }

}
