package ch21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yuzhe
 * @since 11/28/18
 */
public class Restaurant {

    Meal meal;
    WaitPerson waitPersion = new WaitPerson(this);
    Chef chef = new Chef(this);
    BusBoy busBoy = new BusBoy(this);
    ExecutorService exec = Executors.newCachedThreadPool();

    public Restaurant(){
        exec.execute(waitPersion);
        exec.execute(chef);
    }

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
    }

}

class BusBoy{
    private Restaurant restaurant;

    public BusBoy(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


}

class Meal {
    private final int orderNum;

    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "orderNum=" + orderNum +
                '}';
    }
}

class WaitPerson implements Runnable{

    private Restaurant restaurant;

    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                synchronized (this){
                    while(restaurant.meal == null){
                        wait();
                    }
                }
                System.out.println("waitpersion got" + restaurant.meal);
                synchronized (restaurant.chef){
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
                synchronized (restaurant.busBoy){
                    //do sth
                }
            }
        }catch (InterruptedException e){
            System.out.println("waitperson interrupted");
        }
    }
}

class Chef implements Runnable{

    private Restaurant restaurant;
    private int count = 0;

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                synchronized (this){
                    while(restaurant.meal != null){
                        wait();
                    }
                    if(++count == 10){
                        System.out.println("out of food. cloosing");
                        restaurant.exec.shutdownNow();
                        return;
                    }
                    System.out.println("order up!");
                    synchronized (restaurant.waitPersion){
                        restaurant.meal = new Meal(count);
                        restaurant.waitPersion.notifyAll();
                    }
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            }
        } catch (InterruptedException e){
            System.out.println("chef interrupted");
        }
    }
}
