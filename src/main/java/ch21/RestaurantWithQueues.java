package ch21;

import ch19.Course;
import ch19.Food;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author yuzhe
 * @since 12/3/18
 */
public class RestaurantWithQueues {

    public static void main(String[] args) throws IOException {
        ExecutorService exec = Executors.newCachedThreadPool();
        RestaurantHere restaurant = new RestaurantHere(exec, 5, 2);
        exec.execute(restaurant);
        System.in.read();
        exec.shutdownNow();
    }

}

class Order {
    private static int counter = 0;
    private final int id = counter++;
    private final CustomerHere customer;
    private final WaitPersonHere waitPerson;
    private final Food food;

    public Order(CustomerHere customer, WaitPersonHere waitPerson, Food food) {
        this.customer = customer;
        this.waitPerson = waitPerson;
        this.food = food;
    }

    public CustomerHere getCustomer() {
        return customer;
    }

    public WaitPersonHere getWaitPerson() {
        return waitPerson;
    }

    public Food item() {
        return food;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", customer=" + customer
                + ", waitPerson=" + waitPerson + ", food=" + food + '}';
    }
}

class Plate {
    private final Order order;
    private final Food food;

    public Plate(Order order, Food food) {
        this.order = order;
        this.food = food;
    }

    public Order getOrder() {
        return order;
    }

    public Food getFood() {
        return food;
    }

    @Override
    public String toString() {
        return "Plate{" + "order=" + order + ", food=" + food + '}';
    }
}

class CustomerHere implements Runnable {

    private static int counter = 0;
    private final int id = counter++;
    private final WaitPersonHere waitPerson;

    private SynchronousQueue<Plate> placeSetting = new SynchronousQueue<>();

    public CustomerHere(WaitPersonHere waitPerson) {
        this.waitPerson = waitPerson;
    }

    public void deliver(Plate p) throws InterruptedException {
        placeSetting.put(p);
    }

    @Override
    public void run() {
        for (Course course : Course.values()) {
            Food food = course.selectRandom();
            try {
                //order
                synchronized (waitPerson) {
                    waitPerson.placeOrder(this, food);
                }
                //eat placeSetting sync
                System.out.println(this + " is eating " + placeSetting.take());
            } catch (InterruptedException e) {
                System.out.println(this + " is waiting for " + course + " interrupted");
                break;
            }
        }
        System.out.println(this + " finished meal, leaving.");
    }

    @Override
    public String toString() {
        return "CustomerHere{" + "id=" + id + '}';
    }
}

class WaitPersonHere implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final RestaurantHere restaurant;
    BlockingQueue<Plate> filledOrders = new LinkedBlockingQueue<>();

    public WaitPersonHere(RestaurantHere restaurant) {
        this.restaurant = restaurant;
    }

    public void placeOrder(CustomerHere customer, Food food) {
        try {
            restaurant.orders.put(new Order(customer, this, food));
        } catch (InterruptedException e) {
            System.out.println(this + " placeOrder interrupted");
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Plate plate = filledOrders.take();
                System.out.println(this + " received " + plate +
                        " delivering to " + plate.getOrder().getCustomer());
                plate.getOrder().getCustomer().deliver(plate);
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this + " off duty");
    }

    @Override
    public String toString() {
        return "WaitPersonHere{" + "id=" + id + '}';
    }
}

class ChefHere implements Runnable {
    private static int couter = 0;
    private final int id = couter++;
    private final RestaurantHere restaurant;
    private static Random random = new Random(47);

    ChefHere(RestaurantHere restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Order order = restaurant.orders.take();
                Food requestedItem = order.item();
                TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                Plate plate = new Plate(order, requestedItem);
                order.getWaitPerson().filledOrders.put(plate);
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this + " off duty");
    }

    @Override
    public String toString() {
        return "ChefHere{" + "id=" + id + '}';
    }
}

class RestaurantHere implements Runnable {
    private List<WaitPersonHere> waitPersons = new ArrayList<>();
    private List<ChefHere> chefs = new ArrayList<>();
    BlockingQueue<Order> orders = new LinkedBlockingQueue<>();

    private static Random random = new Random(47);
    private ExecutorService exec;

    public RestaurantHere(ExecutorService exec, int nWaitPersons, int nChefs) {
        this.exec = exec;
        for (int i = 0; i < nWaitPersons; i++) {
            WaitPersonHere waitPersonHere = new WaitPersonHere(this);
            waitPersons.add(waitPersonHere);
            exec.execute(waitPersonHere);
        }
        for (int i = 0; i < nChefs; i++) {
            ChefHere chef = new ChefHere(this);
            chefs.add(chef);
            exec.execute(chef);
        }
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                WaitPersonHere waitPerson = waitPersons.get(random.nextInt(waitPersons.size()));
                CustomerHere customerHere = new CustomerHere(waitPerson);
                exec.execute(customerHere);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch (InterruptedException e){
            System.out.println("restaurant interrupted");
        }
        System.out.println("restaurant closing");
    }
}
