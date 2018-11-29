package ch21;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author yuzhe
 * @since 11/29/18
 */
public class HorseRace {

    static final int FINISH_LINE = 25;
    private List<Horse> horses = new ArrayList<>();
    private ExecutorService exec = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;

    public HorseRace(int nhorses, final int pause) {
        barrier = new CyclicBarrier(nhorses, () -> {
            StringBuilder s = new StringBuilder();
            for(int i = 0; i <  FINISH_LINE; i++){
                s.append('=');
            }
            System.out.println(s.toString());
            //打印每一批马的轨迹
            for(Horse horse : horses){
                System.out.println(horse.tracks());
            }
            //比赛是否终结
            for(Horse horse : horses){
                if(horse.getStrides() >= FINISH_LINE){
                    System.out.println(horse + " won!");
                    exec.shutdownNow();
                    return;
                }
            }
            try{
                TimeUnit.MILLISECONDS.sleep(pause);
            } catch (InterruptedException e) {
                System.out.println("barrier-action sleep interrupted");
            }
        });
        for(int i = 0; i < nhorses; i++){
            Horse horse = new Horse(barrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {
        int nHorses = 7;
        int pause = 200;
        new HorseRace(nHorses, pause);
    }

}

class Horse implements Runnable {

    private static int counter = 0;
    private final int id = counter++;
    private int strides = 0;
    private static Random random = new Random(47);
    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier arg){
        barrier = arg;
    }

    public synchronized int getStrides() {
        return strides;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    strides += random.nextInt(3);
                }
                //必须在栅栏处等待其他所有线程准备完毕
                barrier.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Horse{" + "id=" + id + '}';
    }

    public String tracks(){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < getStrides(); i++){
            s.append('*');
        }
        s.append('*');
        return s.toString();
    }
}
