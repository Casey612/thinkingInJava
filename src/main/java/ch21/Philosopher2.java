package ch21;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author yuzhe
 * @since 11/28/18
 */
public class Philosopher2 implements Runnable {

    private final BlockingQueue<Chopstick> chopsticks;
    private final int id;
    private final int ponderFactor;
    private Random random = new Random(47);

    private Chopstick left;
    private Chopstick right;

    public Philosopher2(BlockingQueue<Chopstick> chopsticks, int id, int ponderFactor) {
        this.chopsticks = chopsticks;
        this.id = id;
        this.ponderFactor = ponderFactor;
    }

    private void pause() throws InterruptedException {
        if (ponderFactor == 0) {
            return;
        }
        TimeUnit.MILLISECONDS.sleep(random.nextInt(250 * ponderFactor));
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " is thinking");
                //may sleep
                pause();
                useChopsticks();
                pause();
                dropChopsticks();
            }
        } catch (InterruptedException e) {
            System.out.println(this + " exiting via interrupted");
        }
    }

    private void dropChopsticks() {
        chopsticks.add(left);
        chopsticks.add(right);
        left = null;
        right = null;
    }

    private void useChopsticks() throws InterruptedException {
        synchronized (this){
            while(chopsticks.size() < 2){
                wait();
            }
            if (chopsticks.size() >= 2) {
                synchronized (chopsticks){
                    System.out.println(this + " takes 1");
                    left = chopsticks.take();
                    System.out.println(this + " takes 2");
                    right = chopsticks.take();
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Philosopher{" +
                "id=" + id +
                '}';
    }
}
