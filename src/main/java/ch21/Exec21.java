package ch21;

import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: yuki
 * @date: 2018/11/25
 */
public class Exec21 {

    public static void main(String[] args) throws InterruptedException {
        Task1 task1 = new Task1();
        Task2 task2 = new Task2(task1);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(task1);
        exec.execute(task2);
        exec.shutdown();
        //以线程的方式启动，自然结束。 线程池会等。
//        new Thread(task1).start();
//        new Thread(task2).start();
    }

}

class Task1 implements Runnable{
    @Override
    public synchronized void run() {
        try {
            System.out.println("task1 start");
            wait();
            System.out.println("task1 end");
        } catch (InterruptedException e) {
            System.out.println("task1 interrupted");
        }
    }
}

class Task2 implements Runnable{

    private Task1 task1;

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(5);
            synchronized(task1) {
                // method calling notifyAll() must own monitor
                System.out.println("task2 try to notifyAll()");
                task1.notifyAll();
            }
        } catch (InterruptedException e) {
            System.out.println("task2 interrupted");
        }
    }

    public Task2(Task1 task1) {
        this.task1 = task1;
    }
}
