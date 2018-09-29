package thinkingInJava.ch09;

/**
 * @author yuzhe
 * @since 8/14/18
 */
public class AbstractMain {

    public static void main(String[] args) {
        AbstractTest test = new Test();
        Test.staticMethod(test);
    }

}
