package ch21;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author yuzhe
 * @since 11/12/18
 */
public class Exec5 implements Callable<Integer> {

    private static final int size = 25;
    public static final Integer[] array = new Integer[size];

    public int n;

    public Exec5(int n) {
        this.n = n;
    }


    private int gen(int i) {
        if (i == 0) {
            return 0;
        } else if (i == 1) {
            return 1;
        } else {
            return array[i - 1] + array[i - 2];
        }
    }

    @Override
    public Integer call() throws Exception {
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (Objects.isNull(array[i])) {
                array[i] = gen(i);
            }
            result += array[i];
        }
        return result;
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Random random = new Random(47);

        List<Future<Integer>> results = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            results.add(exec.submit(new Exec5(random.nextInt(size))));
        }
        for(Future<Integer> result : results){
            try{
                if(result.isDone()){
                    System.out.println(result.get());
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        exec.shutdown();
    }
}
