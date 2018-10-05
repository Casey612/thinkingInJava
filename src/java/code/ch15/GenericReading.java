package code.ch15;

import java.util.Arrays;
import java.util.List;

/**
 * @author yuzhe
 * @since 9/6/18
 */
public class GenericReading {

    static List<Apple> apples = Arrays.asList(new Apple());
    static List<Fruit> fruits = Arrays.asList(new Fruit());

    static <T> T readExact(List<T> list) {
        return list.get(0);
    }

    static void f1() {
        System.out.println("----f1----");
        Apple apple = readExact(apples);
        Fruit fruit = readExact(fruits);
        Fruit fruit1 = readExact(apples);
        System.out.println("apple:" + apple);
        System.out.println("fruit:" + fruit);
        System.out.println("fruit1:" + fruit1);
    }

    static class Reader<T> {
        T readExact(List<T> list) {
            return list.get(0);
        }
    }

    static void f2() {
        System.out.println("----f2----");
        Reader<Fruit> fruitReader = new Reader<>();
        Fruit fruit = fruitReader.readExact(fruits);
//        Fruit fruit1 = fruitReader.readExact(apples); compile error
    }

    static class CovariantReader<T> {
        T readCovariant(List<? extends T> list) {
            return list.get(0);
        }
    }

    static void f3(){
        System.out.println("----f3----");
        CovariantReader<Fruit> covariantReader = new CovariantReader<>();
        Fruit f1 = covariantReader.readCovariant(fruits);
        Fruit f2 = covariantReader.readCovariant(apples);
        System.out.println(f1);
        System.out.println(f2);
    }

    public static void main(String[] args) {
        f1();
        f2();
        f3();
    }
}
