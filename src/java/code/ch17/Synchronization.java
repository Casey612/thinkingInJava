package code.ch17;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author: yuki
 * @date: 2018/10/7
 */
public class Synchronization {

    public static void main(String[] args) {
        Collection<String> c = Collections.synchronizedCollection(new ArrayList<>());
    }

}
