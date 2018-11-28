package ch21;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author yuzhe
 * @since 11/28/18
 */
public class Exec29 {

    public static void main(String[] args) throws InterruptedException {
        ToastQueue dryQueue = new ToastQueue();
        ToastQueue peanutButteredQueue = new ToastQueue();
        ToastQueue jellyQueue = new ToastQueue();
        SandwichQueue sandwichQueue = new SandwichQueue();

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ToastMaker(dryQueue));
        exec.execute(new PeanutButterer(dryQueue, peanutButteredQueue));
        exec.execute(new Jellyer(dryQueue, jellyQueue));
        exec.execute(new SandwichMaker(peanutButteredQueue, jellyQueue, sandwichQueue));
        exec.execute(new SandwichEater(sandwichQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }

}

class Sandwich{
    private static int  count = 0;
    private Toast top, buttom;

    public Sandwich(Toast top, Toast buttom) {
        this.top = top;
        this.buttom = buttom;
        count++;
    }

    @Override
    public String toString() {
        return "Sandwich{" +
                "top=" + top +
                ", buttom=" + buttom +
                '}';
    }
}

class ToastMaker implements Runnable{

    private ToastQueue toastQueue;

    private Random rand = new Random(47);
    private static int count = 0;

    public ToastMaker(ToastQueue toastQueue) {
        this.toastQueue = toastQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(100));
                Toast toast = new Toast(count++);
                System.out.println(toast);
                toastQueue.put(toast);
            }
        } catch (InterruptedException e) {
            System.out.println("toaster interrupted");
        }
        System.out.println("toaster off");
    }
}


class PeanutButterer implements Runnable {

    private ToastQueue dryQueue, peanutButteredQueue;

    public PeanutButterer(ToastQueue dryQueue, ToastQueue butteredQueue) {
        this.dryQueue = dryQueue;
        this.peanutButteredQueue = butteredQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = dryQueue.take();
                t.peanutButter();
                System.out.println(t);
                peanutButteredQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("PeanutButterer interrupted");
        }
        System.out.println("PeanutButterer off");
    }
}

class Jellyer implements Runnable {

    private ToastQueue dryQueue, jellyedQueue;

    public Jellyer (ToastQueue butteredQueue, ToastQueue finishedQueue) {
        this.dryQueue = butteredQueue;
        this.jellyedQueue = finishedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = dryQueue.take();
                t.jelly();
                System.out.println(t);
                jellyedQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Jellyer interrupted");
        }
        System.out.println("Jellyer off");
    }
}

class SandwichQueue extends LinkedBlockingQueue<Sandwich>{}

class SandwichMaker implements Runnable{

    private ToastQueue butteredQueue;
    private ToastQueue jellyedQueue;
    private SandwichQueue sandwichQueue;

    public SandwichMaker(ToastQueue butteredQueue, ToastQueue jellyedQueue, SandwichQueue sandwiches) {
        this.butteredQueue = butteredQueue;
        this.jellyedQueue = jellyedQueue;
        this.sandwichQueue = sandwiches;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                Toast top = jellyedQueue.take();
                Toast buttom = butteredQueue.take();
                Sandwich sandwich = new Sandwich(top, buttom);
                sandwichQueue.put(sandwich);
            }
        } catch (InterruptedException e){
            System.out.println("sandwicher interrupted");
        }
    }
}

class SandwichEater implements Runnable{

    private SandwichQueue sandwichQueue;

    public SandwichEater(SandwichQueue sandwichQueue) {
        this.sandwichQueue = sandwichQueue;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                Sandwich sandwich = sandwichQueue.take();
                System.out.println(sandwich + " has been eaten");
            }
        } catch (InterruptedException e){
            System.out.println("sandwith eater interrupted");
        }
    }
}
