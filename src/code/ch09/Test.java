package thinkingInJava.ch09;

/**
 * @author yuzhe
 * @since 8/14/18
 */
public class Test extends AbstractTest {

    @Override
    public void say(){
        System.out.println("test");
    }

    public static void staticMethod(AbstractTest abstractTest){
        abstractTest.say();
    }

}
