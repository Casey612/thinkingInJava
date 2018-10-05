package code.ch16;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author yuzhe
 * @since 9/17/18
 */
public class MyList<T> extends ArrayList<T> {

    public void getReversed() {
        Collections.reverse(this);
    }

    public static void main(String[] args) {
        MyList<Integer> myList = new MyList<>();
        myList.addAll(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(myList);
        myList.getReversed();
        System.out.println(myList);
    }

}
