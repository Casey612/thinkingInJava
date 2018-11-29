package ch21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yuzhe
 * @since 11/28/18
 */
public class Exec27Restaurant {

    Meal meal;
    WaitPerson27 waitPersion = new WaitPerson27(this);
    Chef27 chef = new Chef27(this);
    BusBoy27 busBoy = new BusBoy27(this);
    ExecutorService exec = Executors.newCachedThreadPool();

    public Exec27Restaurant() {
        exec.execute(waitPersion);
        exec.execute(chef);
    }

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
    }

}

class BusBoy27 {
    private Exec27Restaurant restaurant;

    public BusBoy27(Exec27Restaurant restaurant) {
        this.restaurant = restaurant;
    }


}

class Meal27 {
    private final int orderNum;

    public Meal27(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "orderNum=" + orderNum +
                '}';
    }
}

class WaitPerson27 implements Runnable {

    private Exec27Restaurant restaurant;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public WaitPerson27(Exec27Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                lock.lock();
                try {
                    while (restaurant.meal == null) {
                        condition.await();
                    }
                } finally {
                    lock.unlock();
                }

                System.out.println("waitpersion got" + restaurant.meal);

                restaurant.chef.lock.lock();
                try{
                    restaurant.meal = null;
                    restaurant.chef.condition.signalAll();
                }finally {
                    restaurant.chef.lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("waitperson interrupted");
        }
    }
}

class Chef27 implements Runnable {

    private Exec27Restaurant restaurant;
    private int count = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    public Chef27(Exec27Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                lock.lock();
                try {
                    while (restaurant.meal != null) {
                        condition.await();
                    }
                    if (++count == 10) {
                        System.out.println("out of food. cloosing");
                        restaurant.exec.shutdownNow();
                        return;
                    }
                    System.out.println("order up!");
                } finally {
                    lock.unlock();
                }

                restaurant.waitPersion.lock.lock();
                try{
                    restaurant.meal = new Meal(count);
                    restaurant.waitPersion.condition.signalAll();
                }finally {
                    restaurant.waitPersion.lock.unlock();
                }

            }
        } catch (InterruptedException e) {
            System.out.println("chef interrupted");
        }
    }
}
