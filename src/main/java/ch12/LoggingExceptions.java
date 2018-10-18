package ch12;

/**
 * @author yuzhe
 * @since 8/23/18
 */
public class LoggingExceptions {

    public static void main(String[] args) {
        try {
            throw new LoggingException();
        } catch (LoggingException e) {
            System.err.println("catch " + e);
        }
        try {
            throw new LoggingException();
        } catch (LoggingException e) {
            System.err.println("catch " + e);
        }
    }

}
