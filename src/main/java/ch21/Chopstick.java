package ch21;

/**
 * @author yuzhe
 * @since 11/28/18
 */
public class Chopstick {

    private boolean taken = false;

    public synchronized void take() throws InterruptedException {
        while(taken){
            wait();
        }
        taken = true;
    }

    public synchronized  void drop(){
        taken = false;
        notifyAll();
    }

}
