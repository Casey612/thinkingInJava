package java.ch17;

import groovy.util.MapEntry;

import java.util.*;

/**
 * @author: yuki
 * @date: 2018/10/6
 */
public class SimpleHashMap<K, V> extends AbstractMap<K, V> {

    private final int SIZE = 997;

    LinkedList<Map.Entry<K, V>>[] buckets = new LinkedList[SIZE];

    @Override
    public V put(K key, V value) {
        V oldValue = null;

        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }
        LinkedList<Map.Entry<K, V>> bucket = buckets[index];
        Map.Entry<K, V> pair = new MapEntry(key, value);
        boolean found = false;

        ListIterator<Entry<K, V>> it = bucket.listIterator();
        while (it.hasNext()) {
            Map.Entry<K, V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                it.set(pair);
                found = true;
                break;
            }
        }
        if (!found) {
            buckets[index].add(pair);
        }
        return oldValue;
    }

    @Override
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            return null;
        }
        for (Map.Entry<K, V> entry : buckets[index]) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }


    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (LinkedList<Map.Entry<K, V>> bucket : buckets) {
            if(bucket == null){
                continue;
            }
            for(Map.Entry<K, V> mPair : bucket){
                set.add(mPair);
            }
        }
        return set;
    }
}
