package thinkingInJava.ch17;

/**
 * @author yuzhe
 * @since 9/25/18
 */

import java.util.*;

public class SubListTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            list.add(i);
        }
        List<Integer> sub = list.subList(1, 6);
        System.out.println(list);
        System.out.println(sub);
        sub.set(2, 99);
        System.out.println(list);
        System.out.println(sub);
    }

}
