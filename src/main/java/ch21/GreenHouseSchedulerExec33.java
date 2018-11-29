package ch21;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author: yuki
 * @date: 2018/11/29
 */
abstract class GreenhouseTask implements Delayed, Runnable {
    protected long delayTime;
    // in MILLISECONDS
    public long trigger;
    // in NANOSECONDS

    /**
     * 立刻执行
     */
    public GreenhouseTask() {
        delayTime = 0;
        trigger = System.nanoTime();
    }

    /**
     * start = 0 delay = d
     * @param d
     */
    public GreenhouseTask(long d) {
        delayTime = d;
        trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS);
    }

    /**
     * start = start delay = d
     * @param d
     */
    public GreenhouseTask(long start, long d) {
        delayTime = d;
        trigger = TimeUnit.NANOSECONDS.convert(start, TimeUnit.MILLISECONDS)
                + TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(
                trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
    }
    @Override
    public int compareTo(Delayed d) {
        GreenhouseTask that = (GreenhouseTask)d;
        if(trigger < that.trigger) {
            return -1;
        }
        if(trigger > that.trigger) {
            return 1;
        }
        return 0;
    }
    @Override
    abstract public void run();
    abstract public GreenhouseTask create(long start, long delay);
}


public class GreenHouseSchedulerExec33 {

    private volatile Boolean light = false;
    private volatile Boolean water = false;
    private String thermostat = "Day";

    public synchronized String getThermostat() {
        return thermostat;
    }

    public synchronized void setThermostat(String thermostat) {
        this.thermostat = thermostat;
    }

    //线程池
    DelayQueue<GreenhouseTask> queue = new DelayQueue<>();

    public void schedule(GreenhouseTask event, long delay) {
        queue.add(event.create(0, delay));
    }

    public void repeat(GreenhouseTask task, long start, long interval, long end) {
        while(start <= end){
            queue.add(task.create(start, interval));
            start += interval;
        }
    }

    class LightOn extends GreenhouseTask {
        public LightOn() {
            super();
        }

        public LightOn(long d) {
            super(d);
        }

        public LightOn(long start, long d) {
            super(start, d);
        }

        @Override
        public void run() {
            try {
                //light == false -> on
                synchronized (this){
                    while (light) {
                        wait();
                    }
                    synchronized (light) {
                        System.out.println("turning on lights");
                        light = true;
                    }
                }
            } catch (InterruptedException e) {
                // nothing
            }
        }

        @Override
        public LightOn create(long start, long delay) {
            return new LightOn(start, delay);
        }
    }

    class LightOff extends GreenhouseTask{
        public LightOff() {
            super();
        }

        public LightOff(long d) {
            super(d);
        }

        public LightOff(long start, long d) {
            super(start, d);
        }

        @Override
        public void run() {
            try {
                //light == true -> off
                synchronized (this){
                    while (!light) {
                        wait();
                    }
                    synchronized (light) {
                        System.out.println("turning off lights");
                        light = false;
                    }
                }
            } catch (InterruptedException e) {
                // nothing
            }
        }

        @Override
        public LightOff create(long start, long delay) {
            return new LightOff(start, delay);
        }
    }

    class WaterOn extends GreenhouseTask {
        public WaterOn() {
            super();
        }

        public WaterOn(long d) {
            super(d);
        }

        public WaterOn(long start, long d) {
            super(start, d);
        }

        @Override
        public void run() {
            try {
                synchronized (this){
                    while (water) {
                        wait();
                    }
                    synchronized (light) {
                        System.out.println("turning greenhouse water on");
                        water = true;
                    }
                }
            } catch (InterruptedException e) {
                // nothing
            }
        }

        @Override
        public WaterOn create(long start, long delay) {
            return new WaterOn(start, delay);
        }
    }

    class WaterOff extends GreenhouseTask{
        public WaterOff() {
            super();
        }

        public WaterOff(long d) {
            super(d);
        }

        public WaterOff(long start, long d) {
            super(start, d);
        }

        @Override
        public void run() {
            try {
                synchronized (this){
                    while (!water) {
                        wait();
                    }
                    synchronized (light) {
                        System.out.println("turning greenhouse water off");
                        water = false;
                    }
                }
            } catch (InterruptedException e) {
                // nothing
            }


        }

        @Override
        public WaterOff create(long start, long delay) {
            return new WaterOff(start, delay);
        }
    }

    class ThermostatNight extends GreenhouseTask{
        public ThermostatNight() {
            super();
        }

        public ThermostatNight(long d) {
            super(d);
        }

        public ThermostatNight(long start, long d) {
            super(start, d);
        }

        @Override
        public void run() {
            System.out.println("thermostat to night setting");
            setThermostat("Night");
        }

        @Override
        public ThermostatNight create(long start, long delay) {
            return new ThermostatNight(start, delay);
        }
    }

    class ThermostatDay extends GreenhouseTask{
        public ThermostatDay() {
            super();
        }

        @Override
        public ThermostatDay create(long start, long delay) {
            return new ThermostatDay(start, delay);
        }

        public ThermostatDay(long d) {
            super(d);
        }

        public ThermostatDay(long start, long d) {
            super(start, d);
        }

        @Override
        public void run() {
            System.out.println("thermostat to day setting");
            setThermostat("Day");
        }
    }

    class Bell extends GreenhouseTask{
        public Bell() {
            super();
        }


        public Bell(long d) {
            super(d);
        }

        public Bell(long start, long d) {
            super(start, d);
        }

        @Override
        public void run() {
            System.out.println("bing!");
        }

        @Override
        public Bell create(long start, long delay) {
            return new Bell(start, delay);
        }
    }

    class Terminate extends GreenhouseTask{
        public Terminate(ExecutorService exec) {
            super();
        }

        public Terminate(long start, long d) {
            super(start, d);
        }

        @Override
        public Terminate create(long start, long delay) {
            return new Terminate(start, delay);
        }

        @Override
        public void run() {
            System.out.println("terminating");
            new Thread(() -> {
                for (DataPoint d : data) {
                    System.out.println(d);
                }
            }).start();
        }
    }

    static class DataPoint {
        final LocalDateTime time;
        final float temperature;
        final float humidity;

        public DataPoint(LocalDateTime time, float temperature, float humidity) {
            this.time = time;
            this.temperature = temperature;
            this.humidity = humidity;
        }

        @Override
        public String toString() {
            return "DataPoint{" + "time=" + time + ", temperature=" + temperature
                    + ", humidity=" + humidity + '}';
        }

    }

    class CollectData extends GreenhouseTask {

        public CollectData() {
            super();
        }

        public CollectData(long start, long d) {
            super(start, d);
        }

        @Override
        public void run() {
            System.out.println("collecting data");
            synchronized (GreenHouseSchedulerExec33.this) {
                lastTime = lastTime.plusMinutes(30);
                if (random.nextInt(5) == 4) {
                    tempDirection = -tempDirection;
                }
                lastTemp = lastTemp + tempDirection * (1.0f + random.nextFloat());
                if (random.nextInt(5) == 4) {
                    humidityDirection = -humidityDirection;
                }
                lastHumidity = lastHumidity + humidityDirection * random.nextFloat();
                data.add(new DataPoint(lastTime, lastTemp, lastHumidity));
            }
        }

        @Override
        public CollectData create(long start, long delay) {
            return new CollectData(start, delay);
        }
    }

    private LocalDateTime lastTime = LocalDateTime.now().minusMinutes(30);
    private float lastTemp = 65.0f;
    private int tempDirection = 1;
    private float lastHumidity = 50.0f;
    private int humidityDirection = 1;
    private Random random = new Random(47);
    List<DataPoint> data = Collections.synchronizedList(new ArrayList<DataPoint>());

    public static void main(String[] args) throws InterruptedException {
        GreenHouseSchedulerExec33 gh = new GreenHouseSchedulerExec33();
        ExecutorService exec = Executors.newCachedThreadPool();
        gh.schedule(gh.new Terminate(exec), 5000);
        gh.repeat(gh.new Bell(), 0, 1000, 5000);
        gh.repeat(gh.new ThermostatNight(), 0, 2000, 5000);
        gh.repeat(gh.new LightOn(), 0, 200, 5000);
        gh.repeat(gh.new LightOff(), 0, 400, 5000);
        gh.repeat(gh.new WaterOn(), 0, 600, 5000);
        gh.repeat(gh.new WaterOff(), 0, 800, 5000);
        gh.repeat(gh.new ThermostatDay(), 0, 1400, 5000);
        gh.repeat(gh.new CollectData(), 500, 500, 5000);
        while(!gh.queue.isEmpty()){
            exec.execute(gh.queue.take());
        }
        TimeUnit.MILLISECONDS.sleep(5000);
        exec.shutdownNow();
    }
}
