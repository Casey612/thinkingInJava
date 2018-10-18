package java.ch09.filter;

import  java.ch09.processor2.Processor;

/**
 * @author yuzhe
 * @since 8/15/18
 */
public class FilterAdapter implements Processor {

    Filter filter;
    FilterAdapter(Filter f){
        this.filter = f;
    }

    @Override
    public WaveForm process(Object input){
        return filter.process((WaveForm) input);
    }

}
