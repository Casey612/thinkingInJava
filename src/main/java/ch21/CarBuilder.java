package ch21;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author yuzhe
 * @since 12/3/18
 */
public class CarBuilder {

    public static void main(String[] args) throws InterruptedException {
        CarQueue chassisQueue = new CarQueue();
        CarQueue finishingQueue = new CarQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        RobotPool robotPool =new RobotPool();
        exec.execute(new EngineRobot(robotPool));
        exec.execute(new DriveTrainRobot(robotPool));
        exec.execute(new WheelsRobot(robotPool));

        exec.execute(new Assembler(chassisQueue, finishingQueue, robotPool));

        exec.execute(new Reporter(finishingQueue));

        exec.execute(new ChassisBuilder(chassisQueue));

        TimeUnit.SECONDS.sleep(7);
        exec.shutdownNow();
    }

}

class CarHere {
    private final int id;
    private boolean engine = false, driveTrain = false, wheels = false;

    public CarHere() {
        this.id = -1;
    }

    public CarHere(int id) {
        this.id = id;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized void addEngine() {
        this.engine = true;
    }

    public synchronized void addDriveTrain() {
        this.driveTrain = true;
    }

    public synchronized void addWheels() {
        this.wheels = true;
    }

    @Override
    public String toString() {
        return "CarHere{" + "id=" + id + '}';
    }
}

class CarQueue extends LinkedBlockingQueue<CarHere> {
}

class ChassisBuilder implements Runnable {
    private CarQueue carQueue;
    private int counter = 0;

    public ChassisBuilder(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                //origin
                CarHere car = new CarHere(counter++);
                System.out.println("chassisBuilder created " + car);
                carQueue.put(car);
            }
        } catch (InterruptedException e) {
            System.out.println("interrupted: chassisBuilder");
        }
        System.out.println("ChassisBuilder off");
    }
}

class Assembler implements Runnable {
    private CarQueue chassisQueue, finishingQueue;
    private CarHere car;
    private CyclicBarrier barrier = new CyclicBarrier(4);
    private RobotPool robotPool;

    public Assembler(CarQueue chassisQueue, CarQueue finishingQueue, RobotPool robotPool) {
        this.chassisQueue = chassisQueue;
        this.finishingQueue = finishingQueue;
        this.robotPool = robotPool;
    }

    public CyclicBarrier barrier() {
        return barrier;
    }

    public CarHere getCar() {
        return car;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                car = chassisQueue.take();
                robotPool.hire(EngineRobot.class, this);
                robotPool.hire(DriveTrainRobot.class, this);
                robotPool.hire(WheelsRobot.class, this);
                barrier.await();
                finishingQueue.put(car);
            }
        }catch (InterruptedException e){
            System.out.println("assembler interrupted");
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("assembler off");
    }
}

class RobotPool {
    private Set<Robot> pool = new HashSet<>();

    public synchronized void add(Robot r) {
        pool.add(r);
        notifyAll();
    }

    public void hire(Class<? extends Robot> robotType, Assembler d) throws InterruptedException {
        synchronized (this){
            for (Robot r : pool) {
                if (r.getClass().equals(robotType)) {
                    pool.remove(r);
                    r.assignAssembler(d);
                    r.engage();
                    return;
                }
            }
            wait();
            hire(robotType, d);
        }
    }

    public synchronized void release(Robot r){
        add(r);
    }
}

abstract class Robot implements Runnable {

    private RobotPool pool;

    public Robot(RobotPool pool) {
        this.pool = pool;
    }

    protected Assembler assembler;

    public Robot assignAssembler(Assembler assembler) {
        this.assembler = assembler;
        return this;
    }

    private boolean engage = false;

    public synchronized void engage() {
        engage = true;
        notifyAll();
    }

    abstract protected void performService();

    private synchronized void powerDown() throws InterruptedException{
        engage = false;
        assembler = null;
        pool.release(this);
        while (engage == false){
            wait();
        }
    }

    @Override
    public void run() {
        try{
            powerDown();
            while(!Thread.interrupted()){
                performService();
                assembler.barrier().await();
                powerDown();
            }
        }catch (InterruptedException e){
            System.out.println(this + " interrupted");
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}

class EngineRobot extends Robot{

    public EngineRobot(RobotPool pool) {
        super(pool);
    }

    @Override
    protected void performService() {
        System.out.println(this + " installing engine");
        assembler.getCar().addEngine();
    }
}

class DriveTrainRobot extends Robot{
    public DriveTrainRobot(RobotPool pool) {
        super(pool);
    }

    @Override
    protected void performService() {
        System.out.println(this+ " installing driveTrain");
        assembler.getCar().addDriveTrain();
    }
}

class WheelsRobot extends Robot{
    public WheelsRobot(RobotPool pool) {
        super(pool);
    }

    @Override
    protected void performService() {
        System.out.println(this + " installing wheels");
        assembler.getCar().addWheels();
    }
}

class Reporter implements Runnable{
    private CarQueue carQueue;

    public Reporter(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                System.out.println(carQueue.take());
            }
        } catch (InterruptedException e) {
            System.out.println("reporter interrupted");
        }
        System.out.println("reporter off");
    }
}