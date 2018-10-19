package ch19;

import java.util.Random;

/**
 * @author yuzhe
 * @since 10/19/18
 */
public enum CartoonCharacter2 {
    SLAPPY,
    SPANKY,
    PUNCHY,
    TILLY,
    BOUNCY,
    NUTTY,
    BOB;

    private static Random random = new Random(47);

    public static CartoonCharacter2 next() {
        return values()[random.nextInt(values().length)];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(next());
        }
    }

}