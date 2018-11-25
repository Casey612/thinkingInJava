package ch21;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yuzhe
 * @since 11/14/18
 */
public class ExplicitCriticalSection {
    public static void main(String[] args) {
        PairManager pm1 = new ExplicitPairManager1();
        PairManager pm2 = new ExplicitPairManager2();
        CriticalSection.testApproaches(pm1, pm2);
    }

}

class ExplicitPairManager1 extends PairManager {
    private Lock lock = new ReentrantLock();

    @Override
    public synchronized void increment() {
        lock.lock();
        try {
            p.incrementX();
            p.incrementY();
            store(getPair());
        } finally {
            lock.unlock();
        }
    }
}

class ExplicitPairManager2 extends PairManager {
    private ReentrantLock lock = new ReentrantLock();

    /**
     * 需要修改get方法,synchronized锁住对象。
     * 而不是lock,两者交叉使用，会有一些误解。
     * @return
     */
    @Override
    public Pair getPair() {
        lock.lock();
        try{
            return super.getPair();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void increment() {
        Pair temp;
        lock.lock();
        try{
            p.incrementX();
            p.incrementY();
            temp = getPair();
        } finally {
            lock.unlock();
        }
        store(temp);
    }
}
