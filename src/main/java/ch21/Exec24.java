package ch21;

import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yuzhe
 * @since 11/28/18
 */
public class Exec24 {

    final Producer producer;
    final Consumer consumer;
    final Queue<Integer> queue;

    public Exec24(Queue<Integer> queue, int limit) {
        this.queue = queue;
        producer = new Producer(this, limit);
        consumer = new Consumer(this);
    }

    public static void main(String[] args) throws InterruptedException {

        Queue<Integer> queue = new LinkedList<>();
        int limit = 3;
        Exec24 market = new Exec24(queue, limit);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(market.consumer);
        exec.execute(market.producer);
    }

}



class Producer implements Runnable {

    private final Exec24 market;
    private int limit;

    private int num = 0;

    public Producer(Exec24 market, int limit) {
        this.market = market;
        this.limit = limit;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (market.queue.size() == limit) {
                        wait();
                    }
                }
                int temp = num++;
                System.out.println("producer put " + temp);
                market.queue.add(temp);
                synchronized (market.consumer){
                    market.consumer.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("producer interrupted");
        }
    }
}

class Consumer implements Runnable {
    private final Exec24 market;

    public Consumer(Exec24 market) {
        this.market = market;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (market.queue.isEmpty()) {
                        wait();
                    }
                }
                System.out.println("consumer get " + market.queue.remove());
                synchronized (market.producer){
                    market.producer.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(200);
            }
        } catch (InterruptedException e) {
            System.out.println("producer interrupted");
        }
    }
}
