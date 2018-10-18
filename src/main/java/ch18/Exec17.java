package java.ch18;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhe
 * @since 10/10/18
 */
public class Exec17 {

    public static void main(String[] args) {
        Map<Character, Integer> countMap = new HashMap<>(64);

        String content = TextFile.read("src/java/java/ch18/Exec17.java");
        for (int index = 0; index < content.length(); index++) {
            Character c = content.charAt(index);
            if (countMap.containsKey(c)) {
                countMap.put(c, countMap.get(c) + 1);
            } else {
                countMap.put(c, 1);
            }
        }
        System.out.println(countMap);
    }

}
