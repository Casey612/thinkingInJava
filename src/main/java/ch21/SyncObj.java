package ch21;

/**
 * @author: yuki
 * @date: 2018/11/22
 */
public class SyncObj {
    private Integer num = new Integer(0);

    private void increse1(){
        synchronized (num){
            System.out.println(Thread.currentThread().getName() + " start");
            for(int i = 0; i < 5; i++) {
                System.out.println("increment 1");
                num += 1;
                Thread.yield();
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }
    }

    private void increse2(){
        synchronized (num){
            System.out.println(Thread.currentThread().getName() + " start");
            for(int i = 0; i < 5; i++) {
                System.out.println("increment 2");
                num += 2;
                Thread.yield();
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }
    }


    public static void main(String[] args) {
        final SyncObj obj = new SyncObj();
        new Thread(){
            @Override
            public void run() {
                obj.increse1();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                obj.increse2();
            }
        }.start();
    }
}
