package ch10;

/**
 * @author yuzhe
 * @since 8/16/18
 */
public abstract class Event {

    private long eventTime;
    protected final long delayTime;

    protected Event(long delayTime) {
        this.delayTime = delayTime;
        //从对象初始化开始,过delayTime时间后
        start();
    }

    public void start(){
        //从对象初始化开始,过delayTime时间后,即为事件发生时间
        eventTime = System.nanoTime() + delayTime;
    }

    public boolean ready(){
        return System.nanoTime() >= eventTime;
    }

    public abstract void action();

}
