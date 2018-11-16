package ch21;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author yuzhe
 * @since 11/15/18
 */
public class Interrupting {

    private static ExecutorService exec = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException {
        System.out.println("-------------------------");
        Future<?> f = exec.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("interrupting " + r.getClass().getName());
        f.cancel(true);
        System.out.println("interrupt sent to " + r.getClass().getName());
        System.out.println("-------------------------");
    }

    public static void main(String[] args) throws InterruptedException {
        test(new SleepBlocked());
        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
        TimeUnit.SECONDS.sleep(3);
        System.out.println("aborting with system.exit(0)");
        System.exit(0);
    }

}

//睡眠阻塞
class SleepBlocked implements Runnable{

    @Override
    public void run() {
        try{
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("interrupted exception");
        }
        System.out.println("exiting sleepBlocked.run()");
    }
}
//等待IO阻塞
class IOBlocked implements Runnable{
    private InputStream in;

    public IOBlocked(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try{
            System.out.println("waiting for read():");
            in.read();
        } catch (Exception e){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("interrupted from blicked I/O");
            } else {
                System.out.println("caught exception e");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        System.out.println("exiting IOBlocked.run()");
    }
}
//获得锁时阻塞
class SynchronizedBlocked implements Runnable{

    public synchronized void f(){
        while(true){
            Thread.yield();
        }
    }

    public SynchronizedBlocked() {
        new Thread(){
            @Override
            public void run() {
                f();
            }
        }.run();
    }

    @Override
    public void run() {
        System.out.println("trying to call f()");
        f();
        System.out.println("exiting synchronizedBlocked.run()");
    }
}

