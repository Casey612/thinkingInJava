package ch21;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yuzhe
 * @since 11/14/18
 */
public class CriticalSection {
    static void testApproaches(PairManager pman1, PairManager pman2) {
        ExecutorService exec = Executors.newCachedThreadPool();
        PairMunipulator pm1 = new PairMunipulator(pman1),
                pm2 = new PairMunipulator(pman2);
        PairCheck pch1 = new PairCheck(pman1),
                pch2 = new PairCheck(pman2);
        exec.execute(pm1);
        exec.execute(pm2);
        exec.execute(pch1);
        exec.execute(pch2);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("sleep interrupted");
        }
        System.out.println("pm1: " + pm1 + "pm2: " + pm2);
        System.exit(0);
    }

    public static void main(String[] args) {
        PairManager pm1 = new PairManager1();
        PairManager pm2 = new PairManager2();
        testApproaches(pm1, pm2);
    }


}

class Pair {
    private int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair() {
        this(0, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incrementX() {
        x++;
    }

    public void incrementY() {
        y++;
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y;
    }

    public class PairValuesNotEqualException extends RuntimeException {
        public PairValuesNotEqualException() {
            super("pair values not equal: " + Pair.this);
        }
    }

    public void checkState() {
        if (x != y) {
            throw new PairValuesNotEqualException();
        }
    }

}

abstract class PairManager {
    AtomicInteger checkCounter = new AtomicInteger(0);
    protected Pair p = new Pair();
    private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());

    public synchronized Pair getPair() {
        return new Pair(p.getX(), p.getY());
    }

    protected void store(Pair p) {
        storage.add(p);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
        }
    }

    public abstract void increment();

}

class PairManager1 extends PairManager {

    @Override
    public synchronized void increment() {
        p.incrementX();
        p.incrementY();
        store(p);
    }
}

class PairManager2 extends PairManager {
    @Override
    public void increment() {
        Pair temp;
        synchronized (this) {
            p.incrementY();
            p.incrementX();
            temp = p;
        }
        store(temp);
    }
}


class PairMunipulator implements Runnable {

    private PairManager pm;

    public PairMunipulator(PairManager pm) {
        this.pm = pm;
    }

    @Override
    public void run() {
        while (true) {
            pm.increment();
        }
    }

    @Override
    public String toString() {
        return "Pair: " + pm.getPair() + " checkCounter = " + pm.checkCounter.get();
    }
}

class PairCheck implements Runnable {
    private PairManager pm;

    public PairCheck(PairManager pm) {
        this.pm = pm;
    }

    @Override
    public void run() {
        while (true) {
            pm.checkCounter.incrementAndGet();
            pm.getPair().checkState();
        }
    }
}

