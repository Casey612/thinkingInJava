package ch09.processor2;

/**
 * @author yuzhe
 * @since 8/15/18
 */
public abstract class StringProcessor implements Processor {

    public static String s = "If she weighs ths same as a duck, she's made of wood";

    public static void main(String[] args) {
        Apply2.process(new Upcase2(), s);
        Apply2.process(new Downcase2(), s);
        Apply2.process(new Splitter2(), s);
    }

}
