package thinkingInJava.ch15;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * @author yuzhe
 * @since 9/6/18
 */
public class CheckedList {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        addString(list);
        System.out.println(Arrays.toString(list.toArray()));

        System.out.println("------checked list-----");

        List<Integer> integers = Collections.checkedList(new ArrayList<>(), Integer.class);
        integers.add(1);
        addString(integers);
        System.out.println(Arrays.toString(integers.toArray()));
    }

    static void addString(List list){
        list.add("string");
    }

}
