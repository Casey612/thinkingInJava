package java.ch16;

import java.ch15.Generator;

/**
 * @author yuzhe
 * @since 9/17/18
 */
public class Exec13 {

    public static void main(String[] args) {
        Generator<Character> gen = new CountingGenerator.Character();
        String s = createString(gen, 5);
        System.out.println(s);
    }

    private static String createString(Generator<Character> gen, int length) {
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = gen.next();
        }
        return new String(chars);
    }

}
