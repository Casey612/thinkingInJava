package ch17;

import java.util.*;

/**
 * @author: yuki
 * @date: 2018/9/20
 */
public class Countries {

    public static final String[][] DATA = {
            {"ALGERIA", "Algiers"},
            {"ANGOLA", "Luanda"},
            {"CHINA", "Beijing"},
            {"Japan", "Tokyo"}
    };

    private static class FlyweightMap extends AbstractMap<String, String> {


        private static class Entry implements Map.Entry<String, String> {

            int index;

            Entry(int i) {
                this.index = i;
            }

            @Override
            public boolean equals(Object obj) {
                return DATA[index][0].equals(obj);
            }

            @Override
            public String getKey() {
                return DATA[index][0];
            }

            @Override
            public String getValue() {
                return DATA[index][1];
            }

            @Override
            public String setValue(String value) {
                throw new UnsupportedOperationException();
            }

            @Override
            public int hashCode() {
                return DATA[index][0].hashCode();
            }
        }

        static class EntrySet extends AbstractSet<Map.Entry<String, String>> {
            private int size;

            EntrySet(int size) {
                if (size < 0) {
                    this.size = 0;
                } else if (size > DATA.length) {
                    this.size = DATA.length;
                } else {
                    this.size = size;
                }
            }

            @Override
            public int size() {
                return size;
            }

            private class Iter implements Iterator<Map.Entry<String, String>> {
                //数组从0开始，调用next()方法
                private Entry entry = new Entry(-1);

                @Override
                public boolean hasNext() {
                    return entry.index < size - 1;
                }

                @Override
                public Map.Entry<String, String> next() {
                    entry.index++;
                    return entry;
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            }

            @Override
            public Iterator<Map.Entry<String, String>> iterator() {
                return new Iter();
            }
        }

        //默认DATA.length
        private static Set<Map.Entry<String, String>> entries = new EntrySet(DATA.length);

        @Override
        public Set<Map.Entry<String, String>> entrySet() {
            return entries;
        }

    }

    static Map<String, String> select(final int size) {
        return new FlyweightMap() {
            @Override
            public Set<Map.Entry<String, String>> entrySet() {
                return new EntrySet(size);
            }
        };
    }

    static Map<String, String> map = new FlyweightMap();

    public static Map<String, String> capitals(int size) {
        return select(size);
    }

    static List<String> names = new ArrayList<>(map.keySet());

    public static List<String> names() {
        return names;
    }

    public static List<String> names(int size) {
        return new ArrayList<>(select(size).keySet());
    }

    public static void main(String[] args) {
        System.out.println(capitals(2));
        System.out.println(names(2));
    }

}
