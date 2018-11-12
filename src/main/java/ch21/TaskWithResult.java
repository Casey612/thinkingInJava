package ch21;

import java.util.concurrent.Callable;

/**
 * @author yuzhe
 * @since 11/12/18
 */
public class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "result of TaskWithRest-" + id;
    }
}
