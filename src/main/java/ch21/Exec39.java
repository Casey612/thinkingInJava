package ch21;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yuzhe
 * @since 12/3/18
 */
public class Exec39 {

    static final int N_ELEMENTS = 100000;
    static final int N_GENES = 30;
    static final int N_EVOLVERS = 50;
    static final Integer[][] GRID = new Integer[N_ELEMENTS][N_GENES];

    static Random random = new Random(47);

    static class Evolver implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                int element = random.nextInt(N_ELEMENTS);
                synchronized (GRID[element]) {
                    for (int i = 0; i < N_GENES; i++) {
                        int previous = element - 1;
                        if (previous < 1) {
                            previous = N_ELEMENTS - 1;
                        }
                        int next = element + 1;
                        if (next >= N_ELEMENTS) {
                            next = 0;
                        }
                        int oldValue = GRID[element][i];
                        int newValue = oldValue;
                        synchronized (GRID[previous][i]) {
                            newValue += GRID[previous][i];
                        }
                        synchronized (GRID[next][i]) {
                            newValue += GRID[next][i];
                        }
                        newValue /= 3;

                        if (oldValue != newValue) {
                            System.out.println("Old value changed from " + oldValue);
                        }
                        GRID[element][i] = newValue;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < N_ELEMENTS; i++) {
            for (int j = 0; j < N_GENES; j++) {
                GRID[i][j] = random.nextInt(1000);
            }
        }
        for (int i = 0; i < N_EVOLVERS; i++) {
            exec.execute(new Evolver());
        }
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }

}
