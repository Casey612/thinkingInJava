package ch12;

/**
 * @author yuzhe
 * @since 8/27/18
 */
public class C extends B{

    @Override
    public void f() throws MyException {
        System.out.println("C class execute");
        throw new MyException("Exception in C class");
    }

    public static void main(String[] args) {
        A c = new C();
        try {
            c.f();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
