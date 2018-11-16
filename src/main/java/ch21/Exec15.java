package ch21;

/**
 * @author yuzhe
 * @since 11/15/18
 */
public class Exec15 {

    private Integer num = 0;

    public void increment1(){
        synchronized (num){
            System.out.println("-----------------------------");
            System.out.println("before: " + num);
            num++;
            System.out.println("after increase 1 : " + num);
            System.out.println("-----------------------------");
        }
    }

    public void increment2(){
        synchronized (num){
            System.out.println("-----------------------------");
            System.out.println("before: " + num);
            num++;
            num++;
            System.out.println("after increase 2 : " + num);
            System.out.println("-----------------------------");
        }
    }

    public void increment3(){
        synchronized (num){
            System.out.println("-----------------------------");
            System.out.println("before: " + num);
            num++;
            num++;
            num++;
            System.out.println("after increase 3: " + num);
            System.out.println("-----------------------------");
        }
    }


    public static void main(String[] args) {
        Exec15 ref = new Exec15();
        for(int i = 0; i < 5; i++){
            new Thread(new Task1(ref)).start();
            new Thread(new Task2(ref)).start();
            new Thread(new Task3(ref)).start();
        }
    }

}

class Task1 implements Runnable{

    private Exec15 ref;

    public Task1(Exec15 ref) {
        this.ref = ref;
    }

    @Override
    public void run() {
        ref.increment1();
    }
}

class Task3 implements Runnable{

    private Exec15 ref;

    public Task3(Exec15 ref) {
        this.ref = ref;
    }

    @Override
    public void run() {
        ref.increment3();
    }
}

class Task2 implements Runnable{

    private Exec15 ref;

    public Task2(Exec15 ref) {
        this.ref = ref;
    }

    @Override
    public void run() {
        ref.increment2();
    }
}
