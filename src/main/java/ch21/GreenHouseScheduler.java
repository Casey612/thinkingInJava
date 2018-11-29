package ch21;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: yuki
 * @date: 2018/11/29
 */
public class GreenHouseScheduler {

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
    ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(10);

    public void schedule(Runnable event, long delay) {
        scheduler.schedule(event, delay, TimeUnit.MILLISECONDS);
    }

    public void repeat(Runnable event, long initialDelay, long period) {
        scheduler.scheduleAtFixedRate(event, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    class LightOn implements Runnable {
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
    }

    class LightOff implements Runnable {
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
    }

    class WaterOn implements Runnable {
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
    }

    class WaterOff implements Runnable {
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
    }

    class ThermostatNight implements Runnable {
        @Override
        public void run() {
            System.out.println("thermostat to night setting");
            setThermostat("Night");
        }
    }

    class ThermostatDay implements Runnable {
        @Override
        public void run() {
            System.out.println("thermostat to day setting");
            setThermostat("Day");
        }
    }

    class Bell implements Runnable {
        @Override
        public void run() {
            System.out.println("bing!");
        }
    }

    class Terminate implements Runnable {

        @Override
        public void run() {
            System.out.println("terminating");
            scheduler.shutdownNow();
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

    class CollectData implements Runnable {

        @Override
        public void run() {
            System.out.println("collecting data");
            synchronized (GreenHouseScheduler.this) {
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
    }

    private LocalDateTime lastTime = LocalDateTime.now().minusMinutes(30);
    private float lastTemp = 65.0f;
    private int tempDirection = 1;
    private float lastHumidity = 50.0f;
    private int humidityDirection = 1;
    private Random random = new Random(47);
    List<DataPoint> data = Collections.synchronizedList(new ArrayList<DataPoint>());

    public static void main(String[] args) {
        GreenHouseScheduler gh = new GreenHouseScheduler();
        gh.schedule(gh.new Terminate(), 5000);
        gh.repeat(gh.new Bell(), 0, 1000);
        gh.repeat(gh.new ThermostatNight(), 0, 2000);
        gh.repeat(gh.new LightOn(), 0, 200);
        gh.repeat(gh.new LightOff(), 0, 400);
        gh.repeat(gh.new WaterOn(), 0, 600);
        gh.repeat(gh.new WaterOff(), 0, 800);
        gh.repeat(gh.new ThermostatDay(), 0, 1400);
        gh.repeat(gh.new CollectData(), 500, 500);
    }
}
