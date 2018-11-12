package ch21;

import java.util.concurrent.ThreadFactory;

/**
 * @author yuzhe
 * @since 11/12/18
 */
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    }
}
