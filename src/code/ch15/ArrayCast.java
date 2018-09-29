package thinkingInJava.ch15;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author yuzhe
 * @since 9/5/18
 */
public class ArrayCast<T> {

    T[] array;

    public ArrayCast(Class<T> type, int size){
        this.array = (T[]) Array.newInstance(type, size);
    }

    public T[] getArray() {
        return array;
    }

    public void setArray(T[] array) {
        this.array = array;
    }

    public static void main(String[] args) {
        ArrayCast<Integer> intArray = new ArrayCast<>(Integer.class, 5);
        Integer[] array = intArray.getArray();
        System.out.println(Arrays.toString(array));
        array[0] = 99;
        System.out.println(Arrays.toString(array));

        Object[] objects = new Object[2];
        objects[0] = 1;
        objects[1] = 2;
        System.out.println(Arrays.toString(objects));
    }


}
