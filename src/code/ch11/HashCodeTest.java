package thinkingInJava.ch11;

import java.util.*;

/**
 * @author yuzhe
 * @since 8/21/18
 */
public class HashCodeTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("hello", "world1");
        map.put("hi", "world2");
        String[] keys = map.keySet().stream().map(key -> key.toString()).toArray(String[]::new);
        Arrays.sort(keys, Comparator.comparingInt(String::hashCode));
        Map<String, String> map2 = new LinkedHashMap<>();
        for(String key : keys){
            System.out.println(key + ":" + key.hashCode());
            map2.put(key, map.get(key));
        }
        System.out.println(map);
        System.out.println(map2);
    }

}
