package ch21;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author yuzhe
 * @since 12/4/18
 */
public class ActiveObjectDemo {

    private ExecutorService exec = Executors.newSingleThreadExecutor();
    private Random random = new Random(47);

    private void pause(int factor){
        try{
            TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(factor));
        } catch (InterruptedException e) {
            System.out.println("sleep() interrupted");
        }
    }

    public Future<Integer> calculateInt(final int x, final int y){
        return exec.submit(() -> {
            System.out.println("starting " + x + " + " + y);
            pause(200);
            return x + y;
        });
    }

    public Future<Float> calculateFloat(final float x, final float y){
        return exec.submit(() ->{
            System.out.println("starting " + x + " + " + y);
            pause(200);
            return x + y;
        });
    }

    public void shutdown(){
        exec.shutdown();
    }

    public static void main(String[] args) {
        ActiveObjectDemo demo = new ActiveObjectDemo();
        List<Future<?>> results = new CopyOnWriteArrayList<>();

        for(float f = 0.0f; f < 1.0f; f += 0.2f){
            results.add(demo.calculateFloat(f, f));
        }
        for(int i = 0;  i < 5; i ++){
            results.add(demo.calculateInt(i, i));
        }
        System.out.println("al asynch calls made.");
        while(results.size() > 0){
            for (Future<?> f : results){
                if(f.isDone()){
                    try{
                        System.out.println(f.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    results.remove(f);
                }
            }
        }
        demo.shutdown();
    }

}
