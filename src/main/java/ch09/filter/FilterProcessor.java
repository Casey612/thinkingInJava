package java.ch09.filter;

import  java.ch09.processor2.Apply2;

/**
 * @author yuzhe
 * @since 8/15/18
 */
public class FilterProcessor {

    public static void main(String[] args) {
        WaveForm w = new WaveForm();
        Apply2.process(new FilterAdapter(new LowPass(1.0)), w);
        Apply2.process(new FilterAdapter(new HighPass(2.0)), w);
        Apply2.process(new FilterAdapter(new BandPass(3.0, 4.0)), w);
    }

}
