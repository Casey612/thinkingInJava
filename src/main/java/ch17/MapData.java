package ch17;

import ch15.Generator;

import java.util.LinkedHashMap;

/**
 * @author: yuki
 * @date: 2018/9/20
 */
public class MapData<K, V> extends LinkedHashMap<K, V> {

    public MapData(Generator<Pair<K, V>> gen, int quantity) {
        for (int i = 0; i < quantity; i++) {
            Pair<K, V> pair = gen.next();
            put(pair.getKey(), pair.getValue());
        }
    }

    public MapData(Generator<K> genK, Generator<V> genV, int quantity) {
        for (int i = 0; i < quantity; i++) {
            put(genK.next(), genV.next());
        }
    }


    public MapData(Generator<K> genK, V value, int quantity) {
        for (int i = 0; i < quantity; i++) {
            put(genK.next(), value);
        }
    }

    public MapData(Iterable<K> iterable, Generator<V> generator) {
        for(K key : iterable){
            put(key, generator.next());
        }
    }

    public static <K, V> MapData<K, V> map(Generator<Pair<K,V>> generator, int quantity){
        return new MapData<>(generator, quantity);
    }


}

class Pair<K, V> {
    private final K key;
    private final V value;

    Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}