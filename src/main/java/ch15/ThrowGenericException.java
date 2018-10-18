package ch15;

import java.util.List;
import java.util.ArrayList;

/**
 * @author yuzhe
 * @since 9/6/18
 */
public class ThrowGenericException {


}

interface Process<T, E extends Exception> {
    void process(List<T> list) throws E;
}

class ProcessRunner<T, E extends Exception> extends ArrayList<Process<T, E>> {

    List<T> processAll() throws E {
        List<T> resultCollector = new ArrayList<T>();
        for(Process<T, E> process : this){
            process.process(resultCollector);
        }
        return resultCollector;
    }
}