package ch21;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhe
 * @since 11/15/18
 */
public class Exec15ErrorVersion {

//    private final Integer num = new Integer(2000);
    private Integer num = new Integer(2000);
    public void increment1() {
        synchronized (num) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " increment 1");
                num += 1;
                Thread.yield();
            }
        }
    }

    public void increment2() {
        synchronized (num) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " increment 2");
                num += 2;
                Thread.yield();
            }
        }
    }

    public void increment3() {
        synchronized (num) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " increment 3");
                num += 3;
                Thread.yield();
            }
        }
    }


    public static void main(String[] args) {
        final Exec15ListVersion obj = new Exec15ListVersion();
        new Thread() {
            @Override
            public void run() {
                obj.increment1();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                obj.increment2();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                obj.increment3();
            }
        }.start();

    }
}
