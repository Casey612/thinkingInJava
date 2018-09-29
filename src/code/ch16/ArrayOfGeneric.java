package thinkingInJava.ch16;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * @author yuzhe
 * @since 9/13/18
 */
public class ArrayOfGeneric<T> {

    T[] array;
    List<T> list;

    public ArrayOfGeneric(int size) {
        //compile error
//        this.array = new T[size];
        array = (T[]) new Object[size];
        list = new ArrayList<>(size);
    }

    public static <R> R[] makeArray(R r, int size){
//        return new R[size];
        R[] result = (R[]) new Object[size];
        result[0] = r;
        return result;
    }

    public static void main(String[] args) {
        Object[] array = makeArray(1, 10);
        array[0] = "hello";
        array[1] = 1;
        System.out.println(Arrays.toString(array));
    }

}
