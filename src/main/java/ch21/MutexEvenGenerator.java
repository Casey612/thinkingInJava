package ch21;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yuzhe
 * @since 11/14/18
 */
public class MutexEvenGenerator extends IntGenerator{

    private int currentEventValue = 0;
    private Lock lock = new ReentrantLock();

    @Override
    public int next(){
        lock.lock();
        try{
            ++currentEventValue;
            Thread.yield();
            ++currentEventValue;
            return currentEventValue;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        EvenChecker.test(new MutexEvenGenerator());
    }

}
