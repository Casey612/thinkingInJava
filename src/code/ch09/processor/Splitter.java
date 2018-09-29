package thinkingInJava.ch09.processor;

import java.util.Arrays;

/**
 * @author yuzhe
 * @since 8/14/18
 */
public class Splitter extends Processor {

    @Override
    String process(Object input){
        return Arrays.toString(((String)input).split(" "));
    }

}
