package ch19;

import ch15.Generator;

import java.util.Random;

/**
 * @author yuzhe
 * @since 10/19/18
 */
public enum CartoonCharacter implements Generator<CartoonCharacter> {
    SLAPPY,
    SPANKY,
    PUNCHY,
    TILLY,
    BOUNCY,
    NUTTY,
    BOB;

    private Random random = new Random(47);

    @Override
    public CartoonCharacter next() {
        return values()[random.nextInt(values().length)];
    }

}

class EnumImplementation {
    public static <T> void printNext(Generator<T> rg) {
        System.out.println(rg.next() + ",");
    }

    public static void main(String[] args) {
        CartoonCharacter cc = CartoonCharacter.BOB;
        for (int i = 0; i < 10; i++) {
            printNext(cc);
        }
    }
}