package thinkingInJava.ch15;

import java.util.Arrays;

/**
 * @author yuzhe
 * @since 9/5/18
 */
public class ObjectsCast<T> {

    Object[] objects;

    public ObjectsCast(int size) {
        this.objects = new Object[size];
    }

    public void put(T element, int index){
        this.objects[index] = element;
    }

    public T[] getArray(){
        return (T[]) this.objects;
    }

    public static void main(String[] args) {
        ObjectsCast<Double> objectsCast = new ObjectsCast<>(2);
        objectsCast.put(2.2, 0);
        objectsCast.put(1.8, 1);
        System.out.println(Arrays.toString(objectsCast.getArray()));
    }

}
