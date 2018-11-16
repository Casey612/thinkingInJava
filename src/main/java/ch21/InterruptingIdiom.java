package ch21;

import java.util.concurrent.TimeUnit;

/**
 * @author yuzhe
 * @since 11/16/18
 */
public class InterruptingIdiom {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Blocked3());
        t.start();
        TimeUnit.MILLISECONDS.sleep(1000);
        t.interrupt();
    }

}

class NeedsCleanup{
    private final int id;

    public NeedsCleanup(int id) {
        this.id = id;
        System.out.println("NeedsCleanup " + id);
    }

    public void cleanup(){
        System.out.println("cleanup " + id);
    }
}

class Blocked3 implements Runnable{

    private volatile double d= 0.0;

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                NeedsCleanup n1 = new NeedsCleanup(1);
                try{
                    System.out.println("sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    NeedsCleanup n2 = new NeedsCleanup(2);
                    try {
                        System.out.println("calculating");
                        for(int i = 0; i < 2500000; i++){
                            d = d + (Math.PI + Math.E) / d;
                        }
                        System.out.println("finished time-consuming operation.");
                    } finally {
                        n2.cleanup();
                    }
                } finally {
                    n1.cleanup();
                }
            }
            System.out.println("exiting via while() test");
        } catch (InterruptedException e){
            //by sleep
            System.out.println("exit by interruptedException");
        }
    }
}