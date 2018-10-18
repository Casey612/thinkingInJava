package java.ch14;

import java.util.*;

/**
 * @author yuzhe
 * @since 8/30/18
 */
public class FilledList<T> {

    private Class<T> type;

    static {
        System.out.println("FilledList static java block");
    }

    public FilledList(Class<T> type) {
        this.type = type;
    }

    public List<T> create(int num) {
        List<T> result = new ArrayList<T>();
        for (int i = 0; i < num; i++) {
            try {
                result.add(type.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FilledList<CountedInteger> fl = new FilledList<>(CountedInteger.class);
        System.out.println(fl.create(10));
    }

}

class CountedInteger {
    private static long counter;
    private final long id = counter++;

    static {
        System.out.println("CountedInteger static java block");
    }

    @Override
    public String toString() {
        return Long.toString(id);
    }
}
