package ch21;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yuzhe
 * @since 11/29/18
 */
public class CountDownLatchDemo {

    static final int SIZE = 100;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(SIZE);
        for(int i = 0; i < 10; i++){
            exec.execute(new WaitingTask(latch));
        }
        for(int i = 0; i < SIZE; i++){
            exec.execute(new TaskPorting(latch));
        }
        System.out.println("launched all tasks");
        exec.shutdown();
    }

}


class TaskPorting implements Runnable{

    private static int counter = 0;
    private final int id = counter++;
    private static Random random = new Random(47);
    private final CountDownLatch latch;

    TaskPorting(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try{
            doWort();
            latch.countDown();
        } catch (InterruptedException e){
            System.out.println("task porting interrupted");
        }
    }

    private void doWort() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
        System.out.println(this + " completed.");
    }

    @Override
    public String toString() {
        return String.format("%1$-3d ", id);
    }
}

class WaitingTask implements Runnable{

    private static int counter = 0;
    private final int id = counter++;
    private final CountDownLatch latch;

    WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try{
            latch.await();
            System.out.println("latch barrier passed for " + this);
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
    }

    @Override
    public String toString() {
        return String.format("waiting task %1$-3d ", id);
    }
}