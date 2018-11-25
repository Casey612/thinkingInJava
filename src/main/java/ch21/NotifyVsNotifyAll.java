package ch21;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: yuki
 * @date: 2018/11/25
 */
public class NotifyVsNotifyAll {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++){
            exec.execute(new TaskInNoti());
        }
        exec.execute(new TaskInNoti2());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            boolean prod = true;
            @Override
            public void run() {
                if(prod){
                    System.out.println("notify()");
                    TaskInNoti.blocker.prod();
                    prod = false;
                }else {
                    System.out.println("notifyAll()");
                    TaskInNoti.blocker.prodAll();
                    prod = true;
                }
            }
        }, 400, 400);
        TimeUnit.SECONDS.sleep(5);
        timer.cancel();
        System.out.println("Timer canceled");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("task2.blocker.prodAll");
        TaskInNoti2.blocker.prodAll();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("shutting down");
        exec.shutdownNow();
    }

}

class Blocker {
    synchronized void waitingCall() {
        try {
            while (!Thread.interrupted()) {
                wait();
                System.out.println(Thread.currentThread());
            }
        } catch (InterruptedException e) {
            //nothing
        }
    }

    synchronized void prod() {
        notify();
    }

    synchronized void prodAll() {
        notifyAll();
    }
}

class TaskInNoti implements Runnable{

    static Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.waitingCall();
    }
}

class TaskInNoti2 implements Runnable{

    static Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.waitingCall();
    }
}