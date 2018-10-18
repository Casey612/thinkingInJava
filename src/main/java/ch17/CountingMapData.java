package ch17;

import java.util.*;

/**
 * @author yuzhe
 * @since 9/25/18
 */
public class CountingMapData extends AbstractMap<Integer, String> {


    private int size;
    private static String[] chars = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");

    public CountingMapData(int size) {
        this.size = size;
    }

    private static class Entry implements Map.Entry<Integer, String> {
        private int index;

        Entry(int index) {
            this.index = index;
        }

        @Override
        public boolean equals(Object obj) {
            return Integer.valueOf(index).equals(obj);
        }

        @Override
        public Integer getKey() {
            return index;
        }

        @Override
        public String getValue() {
            return chars[index % chars.length] + Integer.toString(index / chars.length);
        }

        @Override
        public String setValue(String value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int hashCode() {
            return Integer.valueOf(index).hashCode();
        }
    }


    private static class EntrySet extends AbstractSet<Map.Entry<Integer, String>> {

        private int size;
        private int index;

        public EntrySet(int size){
            this.size = size;
            this.index = 0;
        }

        @Override
        public Iterator<Map.Entry<Integer, String>> iterator() {
            return new Iterator<Map.Entry<Integer, String>>() {
                @Override
                public boolean hasNext() {
                    return index < size;
                }

                @Override
                public Map.Entry<Integer, String> next() {
                    return new Entry(index++);
                }
            };
        }

        @Override
        public int size() {
            return size;
        }
    }


    @Override
    public Set<Map.Entry<Integer, String>> entrySet() {
//        Set<Map.Entry<Integer, String>> entries = new LinkedHashSet<Map.Entry<Integer, String>>();
//        for(int i = 0; i < size; i++){
//            entries.add(new Entry(i));
//        }
        Set<Map.Entry<Integer, String>> entries = new EntrySet(size);
        return entries;
    }

    public static void main(String[] args) {
        System.out.println(new CountingMapData(48));
    }

}
