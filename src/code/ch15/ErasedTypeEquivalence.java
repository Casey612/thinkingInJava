package thinkingInJava.ch15;

import java.util.*;

/**
 * @author yuzhe
 * @since 9/4/18
 */
public class ErasedTypeEquivalence {

    public static void main(String[] args) {
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c1 == c2);

    }

}
