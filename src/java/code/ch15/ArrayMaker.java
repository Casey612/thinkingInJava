package code.ch15;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author yuzhe
 * @since 9/4/18
 */
public class ArrayMaker<T> {

    private Class<T> kind;

    public ArrayMaker(Class<T> kind) {
        System.out.println(kind);
        this.kind = kind;
    }

    T[] create(int size) {
        return (T[]) Array.newInstance(kind, size);
    }

    public static void main(String[] args) {
        ArrayMaker<Integer> integerArrayMaker = new ArrayMaker<>(Integer.class);
        Integer[] array = integerArrayMaker.create(5);
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString((Integer[]) Array.newInstance(Integer.class, 5)));
        System.out.println(Arrays.toString((int[]) Array.newInstance(int.class, 5)));
    }

}
