package thinkingInJava.ch15;

import java.util.Arrays;

/**
 * @author yuzhe
 * @since 9/5/18
 */
public class CovariantArrays {

    public static void main(String[] args) {
        Fruit[] fruits = new Apple[5];
        fruits[0] = new Apple();
        fruits[1] = new Orange();
        fruits[2] = new Fruit();
        System.out.println(Arrays.toString(fruits));
    }

}

class Fruit {

    @Override
    public String toString() {
        return "fruit";
    }
}

class Apple extends Fruit {
    @Override
    public String toString() {
        return "apple";
    }
}

class Orange extends Fruit {

    @Override
    public String toString() {
        return "orange";
    }
}
