package ch21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author yuzhe
 * @since 11/13/18
 */
public class CaptureUncaughtException {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(new HandleThreadFactory());
        exec.execute(new ExceptionThread2());
    }

}

class ExceptionThread2 implements Runnable{

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by " + t);
        System.out.println("eh = " + t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
    }
}

class HandleThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this + " creating new Thread");
        Thread thread = new Thread(r);
        System.out.println("created " + thread);
        thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("eh = " + thread.getUncaughtExceptionHandler());
        return thread;
    }
}