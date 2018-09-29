package thinkingInJava.ch12.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuzhe
 * @since 8/23/18
 */
public class LoggingTest {

    static final Logger LOG = LoggerFactory.getLogger(LoggingTest.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        LoggingTest console = new LoggingTest();
        console.execute();
    }

    public void execute() {

        if (LOG.isTraceEnabled()) {
            LOG.trace("Test: TRACE level message.");
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("Test: DEBUG level message.");
        }
        if (LOG.isInfoEnabled()) {
            LOG.info("Test: INFO level message.");
        }
        if (LOG.isWarnEnabled()) {
            LOG.warn("Test: WARN level message.");
        }
        if (LOG.isErrorEnabled()) {
            LOG.error("Test: ERROR level message.");
        }
    }

}
