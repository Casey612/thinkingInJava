package thinkingInJava.ch11;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author yuzhe
 * @since 8/20/18
 */
public class CollectionIterator {

    public static void showCollection(Collection collection) {
        for (Object aCollection : collection) {
            System.out.print(aCollection.toString());
        }
    }

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.addAll(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6}));
        showCollection(integers);
    }


}
