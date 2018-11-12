package ch21;

import java.util.concurrent.TimeUnit;

/**
 * @author yuzhe
 * @since 11/12/18
 */
public class ADaemon implements Runnable{


    @Override
    public void run() {
        try{
            System.out.println("starting ADaemon.");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally runs.");
        }
    }

    public static void main(String[] args) {
        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();
    }

}
