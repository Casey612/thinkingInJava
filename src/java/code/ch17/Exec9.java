package code.ch17;

import code.ch16.RandomGenerator;

import java.util.TreeSet;

/**
 * @author yuzhe
 * @since 9/25/18
 */
public class Exec9 {

    public static void main(String[] args) {
        RandomGenerator.String s = new RandomGenerator.String(3);
        TreeSet<String> treeSet = new TreeSet<>();
        for (int i = 0; i < 10; i++) {
            treeSet.add(s.next());
        }
        System.out.println(treeSet);
    }

}