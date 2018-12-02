package ch21;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author: yuki
 * @date: 2018/12/1
 */
public class Pool<T> {

    private int size;
    private List<T> items = new ArrayList<>();
    private volatile boolean[] checkedOut;
    private Semaphore available;

    public Pool(Class<T> classObject, int size) {
        this.size = size;
        checkedOut = new boolean[size];
        available = new Semaphore(size, true);
        for (int i = 0; i < size; i++) {
            try{
                items.add(classObject.newInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public T checkOut() throws InterruptedException {
        available.acquire();
        return getItem();
    }

    //如果items中存在x， 则释放对象
    public void checkIn(T x){
        if(releaseItem(x)){
            available.release();
        }
    }

    /**
     * 释放对象
     * @param x
     * @return
     */
    private synchronized boolean releaseItem(T x) {
        int index = items.indexOf(x);
        if(index == -1) {
            return false;
        }
        if(checkedOut[index]){
            checkedOut[index] = false;
            return true;
        }
        //wasn't checked out
        return false;
    }

    //释放标记，给出item
    private synchronized T getItem(){
        for(int i = 0; i < size; i++){
            if(!checkedOut[i]){
                checkedOut[i] = true;
                return items.get(i);
            }
        }
        //semaphore prevents reaching here
        return null;
    }

}
