package thinkingInJava.ch12;

/**
 * @author yuzhe
 * @since 8/27/18
 */
public class Exec10 {



    private void g() throws MyException {
        throw new MyException("exception thrown in g()");
    }

    private void f() throws Exception {
        try {
            g();
        } catch (MyException e) {
            System.out.println("catch exception in f() start");
            e.printStackTrace(System.out);
            System.out.println("catch exception in f() end");
            throw new Exception(e);
        }
    }

    public static void main(String[] args) {
        Exec10 exec10 = new Exec10();
        try {
            exec10.f();
        } catch (Exception e) {
            System.out.println("catch exception in main");
            e.printStackTrace(System.out);
        }
    }

}
