package thinkingInJava.ch12;

/**
 * @author yuzhe
 * @since 8/27/18
 */
public class A {

    public void f() throws MyException {
        System.out.println("A class execute");
        throw new MyException("Exception in A class");
    }

}
