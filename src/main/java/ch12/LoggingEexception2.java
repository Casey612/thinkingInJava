package java.ch12;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * @author yuzhe
 * @since 8/27/18
 */
public class LoggingEexception2 {

    private static Logger logger = Logger.getLogger("LogException");

    public static void logException(Exception e) {
        StringWriter trace = new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }

    public static void main(String[] args) {
        try {
            throw new NullPointerException();
        } catch (Exception e) {
            logException(e);
        }
    }


}
