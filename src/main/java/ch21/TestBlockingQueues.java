package ch21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.*;

/**
 * @author yuzhe
 * @since 11/27/18
 */
public class TestBlockingQueues {

    static void getKey() {
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void getKey(String msg) {
        System.out.println(msg);
        getKey();
    }

    static void test(String msg, BlockingQueue<LiftOff> queue) {
        System.out.println(msg);
        LiftOffRunner runner = new LiftOffRunner(queue);
        LiftOffAdder adder = new LiftOffAdder(queue);
//        Thread t = new Thread(runner);
//        t.start();
//        for (int i = 0; i < 5; i++) {
//            runner.add(new LiftOff(5));
//        }
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<?> future = exec.submit(runner);
        exec.execute(adder);


        getKey("press enter (" + msg + ")");
        future.cancel(true);
        exec.shutdown();
        System.out.println("finished " + msg + " test");
    }

    public static void main(String[] args) {
//        test("LinkedBlockingQueue", new LinkedBlockingQueue<LiftOff>());
        // use adder Queue full
//        test("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(3));
        test("SynchronousQueue", new SynchronousQueue<LiftOff>());
    }


}

class LiftOffAdder implements Runnable {

    private BlockingQueue<LiftOff> queue;
    private int size = 5;

    public LiftOffAdder(BlockingQueue<LiftOff> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < size; i++) {
            try {
                queue.put(new LiftOff(5));
            } catch (InterruptedException e) {
                System.out.println("adder interrupt");
            }
        }
    }
}


class LiftOffRunner implements Runnable {

    private BlockingQueue<LiftOff> rockets;

    public LiftOffRunner(BlockingQueue<LiftOff> rockets) {
        this.rockets = rockets;
    }

    public void add(LiftOff lo) {
        try {
            rockets.put(lo);
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                LiftOff rocket = rockets.take();
                rocket.run();
            }
        } catch (InterruptedException e) {
            System.out.println("waking from take()");
        }
        System.out.println("exiting liftOffRunner");
    }
}
