package thinkingInJava.ch12;

/**
 * @author yuzhe
 * @since 8/27/18
 */
public class NeverCaught {

    static void f(){
        throw new java.lang.RuntimeException("RuntimeException in f()");
    }

    static void g(){
        f();
    }

    public static void main(String[] args) {
        g();
    }

}
