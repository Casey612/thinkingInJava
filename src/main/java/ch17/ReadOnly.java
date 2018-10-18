package ch17;

import java.util.*;

/**
 * @author: yuki
 * @date: 2018/10/7
 */
public class ReadOnly {

    static Collection<String> data = new ArrayList<String>(Countries.names);

    public static void main(String[] args) {
        Collection<String> c = Collections.unmodifiableCollection(new ArrayList<>(data));

        System.out.println(c);
        //编译通过，运行是报错，UnsupportedOperationException
//        c.add("one");
//        System.out.println(c);

        List<String> a = Collections.unmodifiableList(new ArrayList<>(data));
        ListIterator<String> lit = a.listIterator();
        //编译通过，运行是报错，UnsupportedOperationException
//        System.out.println(lit.next());
//        lit.add("one");

        Set<String> s = Collections.unmodifiableSet(new HashSet<>(data));
        //编译通过，运行是报错，UnsupportedOperationException
//        System.out.println(s);
//        s.add("one");

        //有序 字典序列
        Set<String> s2 = Collections.unmodifiableSortedSet(new TreeSet<>(data));
        System.out.println(s2);

        Map<String, String> m = Collections.unmodifiableMap(new HashMap<>(Countries.capitals(6)));
        System.out.println(m);
        //编译通过，运行是报错，UnsupportedOperationException
//        m.put("USA", "Washington");

        Map<String, String> mm = Collections.unmodifiableSortedMap(new TreeMap<>(Countries.capitals(6)));
        System.out.println(mm);



    }

}
