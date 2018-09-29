package thinkingInJava.ch16;

/**
 * @author yuzhe
 * @since 9/13/18
 */
public class BerylliumSphere {

    private static long counter;
    private final long id = counter++;

    @Override
    public String toString() {
        return "Sphere " + id;
    }
}
