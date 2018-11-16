package ch21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author yuzhe
 * @since 11/16/18
 */
public class Exec18 {

    public void f(){
        try {
            while(true){
                System.out.println("method f() start");
                Thread.sleep(5000);
                System.out.println("method f() end");
            }
        } catch (InterruptedException e) {
            System.out.println("interrupt");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<?> future = exec.submit(new Task(new Exec18()));
        TimeUnit.SECONDS.sleep(20);
        future.cancel(true);
        exec.shutdown();
    }

}

class Task implements Runnable{

    private Exec18 ele;

    public Task(Exec18 ele) {
        this.ele = ele;
    }

    @Override
    public void run() {
        ele.f();
    }
}
