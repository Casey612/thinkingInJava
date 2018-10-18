package ch14;

import java.util.Random;

/**
 * @author yuzhe
 * @since 8/30/18
 */
public class ClassInitialization {
    public static Random rand = new Random(47);

    public static void main(String[] args) {
        Class initable = Initable.class;
        System.out.println("after creating initable ref");
        System.out.println(Initable.staticFinal);
    }

}

class Initable{
//    static final int staticFinal = new Integer(47);
    static final int staticFinal = 47;
    static final int staticFianl2 = ClassInitialization.rand.nextInt(1000);
    static {
        System.out.println("Initialize Initable");
    }
}
