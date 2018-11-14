package ch21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yuzhe
 * @since 11/14/18
 */
public class CircularSet {

    private int[] array;
    private int length;
    private int index = 0;

    public CircularSet(int size){
        array = new int[size];
        length = size;
        for(int i = 0; i< size; i++){
            array[i] = -1;
        }
    }

    public synchronized void add(int i){
        array[index] = i;
        index = ++index % length;
    }

    public synchronized boolean contains(int val){
        for(int i = 0; i < length;i++){
            if(array[i] == val){
                return true;
            }
        }
        return false;
    }

}

class SerialNumberChecker {

    private static final int SIZE = 10;
    private static CircularSet serials = new CircularSet(1000);
    private static ExecutorService exec = Executors.newCachedThreadPool();

    static class SerialChecker implements Runnable{

        @Override
        public void run() {
            while(true){
                int serial = SerialNumberGenerator.nextSerialNumber();
                if(serials.contains(serial)){
                    System.out.println("duplicate: " + serial);
                    System.exit(0);
                }
                serials.add(serial);
            }
        }
    }


    public static void main(String[] args) {
        for(int i = 0; i< SIZE; i++){
            exec.execute(new SerialChecker());
        }
    }

}
