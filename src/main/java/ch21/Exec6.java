package ch21;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author yuzhe
 * @since 11/12/18
 */
public class Exec6 implements Runnable {

    private static Random random = new Random(47);


    @Override
    public void run() {
        long time = random.nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(time);
            System.out.println("sleep " + time + " seconds");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("interrupted");
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < 25; i++){
            new Thread(new Exec6()).start();
        }
    }


}
