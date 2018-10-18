package code.ch19;

/**
 * @author yuzhe
 * @since 10/18/18
 */
/**
 * @author yuzhe
 * @since 10/18/18
 */

enum Shrubbery {
    GROUND,
    CRAWLING,
    HANGING
}

public class EnumClass {

    public static void main(String[] args) {
        for (Shrubbery s : Shrubbery.values()) {
            System.out.println(s + ".ordinal = " + s.ordinal());
            System.out.println(s + ".compareTo(" + Shrubbery.GROUND + ") = " + s.compareTo(Shrubbery.GROUND));
            System.out.println(s + " == " + Shrubbery.HANGING + " = " + (s == Shrubbery.HANGING));
            System.out.println(s.getDeclaringClass());
            System.out.println(s.name());
            System.out.println("--------------------------------------");
        }
        for (String s : "GROUND CRAWLING HANGING".split(" ")) {
            Shrubbery shrubbery = Shrubbery.valueOf(s);
            System.out.println(shrubbery);
        }

    }

}
