package java.ch09.processor2;

import java.util.Arrays;

/**
 * @author yuzhe
 * @since 8/15/18
 */
public class Splitter2 extends StringProcessor {

    @Override
    public String process(Object input){
        return Arrays.toString(((String)input).split(" "));
    }

}
