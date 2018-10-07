package code.ch17;

import java.util.*;

/**
 * @author: yuki
 * @date: 2018/10/6
 */
public class ListSortSearch {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Utilities.list);
        list.addAll(list);
        System.out.println(list);
        Collections.shuffle(list, new Random(47));
        System.out.println("suffle: " + list);

        ListIterator<String> it = list.listIterator(10);
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        System.out.println("trimmed: " + list);

        Collections.sort(list);
        String key = list.get(7);
        System.out.println("sorted: " + list);
        int index = Collections.binarySearch(list, key);
        System.out.println("location of (" + key + ") is " + index
                + ", list.get(" + index + ") = " + list.get(index));

        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        System.out.println("Case-insensitive sorted: " + list);
        key = list.get(7);
        index = Collections.binarySearch(list, key, String.CASE_INSENSITIVE_ORDER);
        System.out.println("location of (" + key + ") is " + index
                + ", list.get(" + index + ") = " + list.get(index));

    }

}
