package ch21;

/**
 * @author yuzhe
 * @since 11/12/18
 */
public class Exec1 {

    public static void main(String[] args) {
        for(int i = 0; i < 25; i++){
            new Thread(new Printer()).start();
        }
    }

    public static class Printer implements Runnable{
        public Printer(){
            System.out.println("constructor of printer");
        }

        @Override
        public void run() {
            for(int i = 0; i < 3; i++){
                System.out.println(Thread.currentThread().getName() + " print " + i + " times.");
                Thread.yield();
            }
            System.out.println(Thread.currentThread().getName() + " exit.");
        }
    }

}
