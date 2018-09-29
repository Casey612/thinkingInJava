package thinkingInJava.ch09.processor;

/**
 * @author yuzhe
 * @since 8/14/18
 */
public class Downcase extends Processor {

    @Override
    String process(Object input){
        return ((String) input).toLowerCase();
    }

}
