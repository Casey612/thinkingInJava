package ch21;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yuzhe
 * @since 11/28/18
 */
public class FixedDiningPhilosophers {

    public static void main(String[] args) throws IOException {
        int ponder = 0;
        int size = 5;

        Chopstick[] chopsticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            chopsticks[i] = new Chopstick();
        }

        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            if (i < (size - 1)) {
                exec.execute(new Philosopher(chopsticks[i], chopsticks[i + 1], i, ponder));
            } else {
                exec.execute(new Philosopher(chopsticks[0], chopsticks[i], i, ponder));
            }
        }
        System.out.println("press enter to quit");
        System.in.read();

        exec.shutdownNow();
    }

}
