package java.ch15;

import java.util.*;

/**
 * @author yuzhe
 * @since 9/6/18
 */
public class CompilerIntelligence {

    public static void main(String[] args) {
        List<? extends Fruit> list = Arrays.asList(new Apple());

//        list.add(new Apple()); compile error
//        list.set(1, new Apple()); compile error

        Apple apple = (Apple) list.get(0);
        System.out.println(list.contains(apple));
        System.out.println(list.contains(new Apple()));
        System.out.println(list.indexOf(apple));
        System.out.println(list.indexOf(new Apple()));

    }

}
