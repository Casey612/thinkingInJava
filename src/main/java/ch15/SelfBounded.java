package java.ch15;

/**
 * @author yuzhe
 * @since 9/6/18
 */
public class SelfBounded<T extends SelfBounded<T>> {
    T t;

    SelfBounded<T> set(T arg) {
        System.out.println("SelfBounded.set()");
        this.t = arg;
        return this;
    }

    T get() {
        return t;
    }
}

class A extends SelfBounded<A> {
    @Override
    A set(A a){
        System.out.println("A.set()");
        return this;
    }

    public static void main(String[] args) {
        A a = new A();
        a.set(a);
    }
}
