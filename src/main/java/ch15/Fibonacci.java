package java.ch15;

import java.util.Iterator;
import java.util.Spliterator;

/**
 * @author yuzhe
 * @since 9/4/18
 */
public class Fibonacci implements Generator<Integer>, Iterable<Integer> {

    private int count = 0;
    private static final int MAX_SIZE = 20;

    private int fib(int n) {
        if (n < 2) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    @Override
    public Integer next() {
        return fib(count++);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return count < MAX_SIZE;
            }

            @Override
            public Integer next() {
                return fib(count++);
            }
        };
    }


    @Override
    public Spliterator<Integer> spliterator() {
        return null;
    }
}
