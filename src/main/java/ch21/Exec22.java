package ch21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: yuki
 * @date: 2018/11/25
 */
public class Exec22 {

    public static void main(String[] args) {
        TaskOne task1 = new TaskOne();
        TaskTwo task2 = new TaskTwo(task1);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(task1);
        exec.execute(task2);
        exec.shutdown();
    }
}

class TaskOne implements Runnable {

    private Boolean flag = false;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(5);
            synchronized (this){
                flag = true;
            }
        } catch (InterruptedException e) {
            System.out.println("task one interrupted");
        }
    }
}

class TaskTwo implements Runnable {

    private TaskOne taskOne;

    public TaskTwo(TaskOne taskOne) {
        this.taskOne = taskOne;
    }

    @Override
    public void run() {
        long timeStart = System.currentTimeMillis();
        while (true) {
            synchronized (taskOne){
                if(taskOne.getFlag()){
                    taskOne.setFlag(false);
                    long period = System.currentTimeMillis() - timeStart;
                    System.out.println("take " + period + " mills");
                    break;
                }
            }
        }
    }
}
