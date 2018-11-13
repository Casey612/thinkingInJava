package ch21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yuzhe
 * @since 11/13/18
 */
public class ExceptionThread implements Runnable{
    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        try{
            //useless thread
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
        } catch (Exception e){
            System.out.println("exception handle here");
        }
    }
}
