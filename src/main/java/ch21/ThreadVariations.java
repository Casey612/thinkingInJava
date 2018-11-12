package ch21;

/**
 * @author yuzhe
 * @since 11/12/18
 */
public class ThreadVariations {

}

class InnerThread2 {
    private int countDown = 5;
    private Thread t;

    public InnerThread2(String name) {
        t = new Thread(name) {
            @Override
            public String toString() {
                return "#" + getName() + "(" + countDown + ")";
            }

            @Override
            public void run() {
                while (true) {
                    System.out.println(this);
                    if (--countDown == 0) {
                        return;
                    }
                }
            }
        };
        t.start();
    }
}

class InnerRunnable1 {
    private int countDown = 5;
    private Inner inner;
    public InnerRunnable1(String name){
        inner = new Inner(name);
    }

    private class Inner implements Runnable{
        Thread t;

        Inner(String name){
            t = new Thread(this, name);
            t.start();
        }
        @Override
        public void run() {
            while (true) {
                System.out.println(this);
                if (--countDown == 0) {
                    return;
                }
            }
        }

        @Override
        public String toString() {
            return "#" + Thread.currentThread().getName() + "(" + countDown + ")";
        }
    }
}