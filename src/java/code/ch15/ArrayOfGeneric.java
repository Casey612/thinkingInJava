package code.ch15;

/**
 * @author yuzhe
 * @since 9/5/18
 */
public class ArrayOfGeneric {

    static final int SIZE = 100;
    static Generic<Integer>[] gia;

    public static void main(String[] args) {
        gia = (Generic<Integer>[]) new Generic[SIZE];
        System.out.println(gia.getClass().getSimpleName());
        gia[0] = new Generic<Integer>();
//        gia[1] = new Generic<Double>(); compile error
    }

}

class Generic<T> {

}
