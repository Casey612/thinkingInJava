package java.ch17;

import java.util.*;

/**
 * @author: yuki
 * @date: 2018/10/6
 */
public class Utilities {

    public static List<String> list
            = Arrays.asList("one Two three Four five Six one".split(" "));

    public static void main(String[] args) {
        System.out.println(list);

        System.out.println("'list' disjoint ('Four')?"
                + Collections.disjoint(list, Collections.singleton("Four")));

        System.out.println("max: " + Collections.max(list));
        System.out.println("min: " + Collections.min(list));

        System.out.println("max(with comparable): " + Collections.max(list, String.CASE_INSENSITIVE_ORDER));
        System.out.println("min(with comparable): " + Collections.min(list, String.CASE_INSENSITIVE_ORDER));


        List<String> sublist = Arrays.asList("Four five Six".split(" "));
        System.out.println("indexOfSublist: " + Collections.indexOfSubList(list, sublist));
        System.out.println("lastIndexOfSublist: " + Collections.lastIndexOfSubList(list, sublist));

        Collections.replaceAll(list, "one", "Yo");
        System.out.println("replaceAll:" + list);
        Collections.reverse(list);
        System.out.println("reverse:" + list);
        Collections.rotate(list, 3);
        System.out.println("rotate:" + list);

        List<String> source = Arrays.asList("in the matrix".split(" "));
        Collections.copy(list, source);
        System.out.println("copy:" + list);

        Collections.swap(list, 0, list.size() - 1);
        System.out.println("swap: " + list);

        Collections.shuffle(list, new Random(47));
        System.out.println("suffle: " + list);

        Collections.fill(list, "pop");
        System.out.println("fill:" + list);

        System.out.println("frequency of 'pop': " + Collections.frequency(list, "pop"));

        List<String> dups = Collections.nCopies(3, "snap");
        System.out.println("dups: " + dups);
        System.out.println("'list' disjoints 'dups': " + Collections.disjoint(list, dups));
        Enumeration<String> e = Collections.enumeration(dups);

        Vector<String> v = new Vector<>();
        while(e.hasMoreElements()){
            v.addElement(e.nextElement());
        }
        ArrayList<String> arrayList = Collections.list(v.elements());
        System.out.println("ArrayList: " + arrayList);

    }


}
