package ch21;

/**
 * @author yuzhe
 * @since 11/12/18
 */
public class BasicThreads {

    public static void main(String[] args) {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("waiting for liftoff");
    }

}
