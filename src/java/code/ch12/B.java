package code.ch12;

/**
 * @author yuzhe
 * @since 8/27/18
 */
public class B extends A {

    @Override
    public void f() throws MyException {
        System.out.println("B class execute");
        throw new MyException("Exception in B class");
    }
}
