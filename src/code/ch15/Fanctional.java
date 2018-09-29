package thinkingInJava.ch15;

import java.util.Iterator;

/**
 * @author yuzhe
 * @since 9/6/18
 */
public class Fanctional {

    public static <T> T reduce(Iterable<T> seq, Combiner<T> combiner) {
        Iterator<T> iterator = seq.iterator();
        if (iterator.hasNext()) {
            T result = iterator.next();
            while (iterator.hasNext()) {
                result = combiner.combine(result, iterator.next());
            }
            return result;
        }
        return null;
    }


    public static <T> Collector<T> forEach(Iterable<T> seq, Collector<T> func){
        for(T t : seq){
            func.function(t);
        }
        return func;
    }

}

interface Combiner<T> {
    T combine(T x, T y);
}

interface UnaryFunction<R, T> {
    R function(T t);
}

interface Collector<T> extends UnaryFunction<T, T> {
    T result();
}

interface UnaryPredicate<T> {
    boolean test(T t);
}