package java.ch12;

/**
 * @author yuzhe
 * @since 8/23/18
 */
public class Exception1 {

    public static void main(String[] args) {
        try {
            throw new Exception("exception created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("finally here");
        }

    }

}
