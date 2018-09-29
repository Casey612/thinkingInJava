package thinkingInJava.ch14;

import java.util.*;

/**
 * @author yuzhe
 * @since 9/3/18
 */
public class AssignableFromTest {

    private static final Map<Class<?>, Integer> COUNTER_MAP = new HashMap<>(8);
    private static Class<?> baseType = A.class;


    private static void countClass(Class<?> c) {
        Integer quantity = COUNTER_MAP.get(c);
        COUNTER_MAP.put(c, quantity == null ? 1 : ++quantity);
        Class<?> superClass = c.getSuperclass();
        if (superClass != null && baseType.isAssignableFrom(superClass)) {
            countClass(superClass);
        }
    }

    private static void count(Object obj) {
        Class<?> type = obj.getClass();
        if(!baseType.isAssignableFrom(type)){
            throw new RuntimeException("incorrect type.");
        }
        countClass(type);
    }

    public static void main(String[] args) {
        A a = new A();
        A b = new B();
        A c = new C();
        List<A> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        for (A item : list){
            count(item);
        }
        System.out.println(COUNTER_MAP);

        System.out.println(A.class.isAssignableFrom(B.class));
        System.out.println(B.class.isAssignableFrom(A.class));
    }


}

class A {
}

class B extends A {
}

class C extends B {
}

