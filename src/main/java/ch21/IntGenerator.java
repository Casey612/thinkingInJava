package ch21;

/**
 * @author yuzhe
 * @since 11/13/18
 */
public abstract class IntGenerator {

    private volatile boolean canceled = false;
    public abstract int next();
    public void cancel(){
        canceled = true;
    }

    public boolean isCanceled(){
        return canceled;
    }

}
