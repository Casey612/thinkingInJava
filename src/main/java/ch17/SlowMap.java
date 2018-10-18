package ch17;

import java.util.*;

/**
 * @author: yuki
 * @date: 2018/10/6
 */
public class SlowMap<K, V> extends AbstractMap<K, V> implements Map<K, V> {

    private ArrayList<K> keys = new ArrayList<>();
    private ArrayList<V> values = new ArrayList<>();

    @Override
    public V put(K key, V value) {
        V oldValue = get(key);
        if (!keys.contains(key)) {
            keys.add(key);
            values.add(value);
        } else {
            values.set(keys.indexOf(key), value);
        }
        return oldValue;
    }

    @Override
    public V get(Object key) {
        if (!keys.contains(key)) {
            return null;
        }
        return values.get(keys.indexOf(key));
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return new EntrySet();
    }

    private class EntrySet extends AbstractSet<Map.Entry<K, V>>{
        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new Iter();
        }

        @Override
        public int size() {
            return keys.size();
        }
    }

    private class Iter implements Iterator<Map.Entry<K, V>>{

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < keys.size();
        }

        @Override
        public Map.Entry<K, V> next() {
            return new Entry(index++);
        }
    }

    private class Entry implements Map.Entry<K, V> {

        private int index;

        Entry(int i){
            this.index = i;
        }

        @Override
        public K getKey() {
            return keys.get(index);
        }

        @Override
        public V getValue() {
            return values.get(index);
        }

        @Override
        public V setValue(V value) {
            return values.set(index, value);
        }
    }

    public static void main(String[] args) {
        SlowMap<Integer, String> map = new SlowMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        System.out.println(map);
        for(Map.Entry<Integer, String> entry : map.entrySet()){
            if(entry.getKey() == 2){
                entry.setValue("Two");
            }
        }
        System.out.println(map);

    }


}
