package ch21;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yuzhe
 * @since 11/16/18
 */
public class Interrupting2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Blocked2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("issuing t.interrupt()");
        t.interrupt();
    }

}

class BlockedMutex {
    private Lock lock = new ReentrantLock();

    public BlockedMutex() {
        lock.lock();
    }

    public void f(){
        try{
            lock.lockInterruptibly();
            System.out.println();
            System.out.println("lock acquired in f();");
        } catch (InterruptedException e) {
            System.out.println("interrupted form lock acquisition in f()");
            System.out.println();
        }
    }
}

class Blocked2 implements Runnable{
    BlockedMutex blockedMutex = new BlockedMutex();
    @Override
    public void run() {
        System.out.println("waiting for f() in blockedMutex");
        blockedMutex.f();
        System.out.println("broken out of blocked call");
    }
}