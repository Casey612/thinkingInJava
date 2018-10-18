package java.ch12.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuzhe
 * @since 8/24/18
 */
public class ThreadsWriteLogTest {

    static final Logger LOG = LoggerFactory.getLogger("FILE_LOGGER");

    /**
     * @param args
     */
    public static void main(String[] args) {

        Thread thread1 = new Thread(new ThreadsWriteLogTest.PrintLogThread());
        Thread thread2 = new Thread(new ThreadsWriteLogTest.PrintLogThread());
        thread1.start();
        thread2.start();
    }

    public static class PrintLogThread implements Runnable {

        @Override
        public void run() {
            ThreadsWriteLogTest console = new ThreadsWriteLogTest();
            try {
                console.execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void execute() throws InterruptedException {

        while (true) {
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
            Thread.sleep(500);
        }

    }

}
