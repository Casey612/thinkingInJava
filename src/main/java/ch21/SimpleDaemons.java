package ch21;

import java.util.concurrent.TimeUnit;

/**
 * @author yuzhe
 * @since 11/12/18
 */
public class SimpleDaemons implements Runnable{
    @Override
    public void run() {
        try{
            while (true){
                TimeUnit.MILLISECONDS.sleep(100l);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("sleep interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 10; i++){
            Thread deamon = new Thread(new SimpleDaemons());
            deamon.setDaemon(true);
            deamon.start();
        }
        System.out.println("all deamon started");
        TimeUnit.MILLISECONDS.sleep(125);
    }
}
