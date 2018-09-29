package thinkingInJava.ch12;

/**
 * @author yuzhe
 * @since 8/27/18
 */
public class ExceptionMethod {

    public static void main(String[] args) {
        try {
            throwException();
        } catch (Exception e) {
            System.out.println("catch exception");
            System.out.println("getMessage():" + e.getMessage());
            System.out.println("getLocalizedMessage():" + e.getLocalizedMessage());
            System.out.println("toString():" + e.toString());
            System.out.println("printStackTrace():");
            e.printStackTrace();
        }
    }

    public static void throwException() throws Exception {
        throw new Exception("my exception");
    }

}
