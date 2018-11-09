package ch19;

import java.util.Random;

/**
 * @author yuzhe
 * @since 10/19/18
 */
public class Enums {

    private static Random rand = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values){
        return values[rand.nextInt(values.length)];
    }

}