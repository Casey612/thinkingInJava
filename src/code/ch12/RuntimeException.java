package thinkingInJava.ch12;

/**
 * @author yuzhe
 * @since 8/23/18
 */
public class RuntimeException {

    public static void main(String[] args) {
        String s = null;
        try {
            System.out.println(s.length());
            s = "abc";
            char[] array = s.toCharArray();
            System.out.println(array[3]);

        } catch (NullPointerException e) {
            System.out.println("String s is null");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("array has no enough elements");
        }
    }

}
