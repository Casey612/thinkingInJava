package ch21;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yuzhe
 * @since 11/28/18
 */
public class Exec31 {

    public static void main(String[] args) throws IOException {
        int ponder = 0;
        int size = 5;
        BlockingQueue<Chopstick> chopsticks = new ArrayBlockingQueue<>(size);
        for(int i = 0; i < size; i++){
//            System.out.println(chopsticks.size());
            chopsticks.add(new Chopstick());
        }

        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0 ; i <  5; i++){
            exec.execute(new Philosopher2(chopsticks, i, ponder));
        }
        System.out.println("press enter to quit");
        System.in.read();

        exec.shutdownNow();
    }

}
