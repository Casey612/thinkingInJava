package code.ch15;

import java.util.*;

/**
 * @author yuzhe
 * @since 9/5/18
 */
public class CovariantArraysForInteger {

    public static void main(String[] args) {
        Number[] numbers = new Integer[3];
        numbers[0] = 1;
        //ArrayStoreException: java.lang.Double
        numbers[1] = 2.2;
        //ArrayStoreException: java.lang.Double
        numbers[3] = 2L;
        System.out.println(Arrays.toString(numbers));
        //compile error
//        List<Number> numbersList = new ArrayList<Integer>();
        List<? extends Number> numbersList = new ArrayList<Integer>();
    }

}
