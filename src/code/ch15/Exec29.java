package thinkingInJava.ch15;

import javax.xml.ws.Holder;
import java.util.List;
import java.util.ArrayList;

/**
 * @author yuzhe
 * @since 9/6/18
 */
public class Exec29 {

    static void f1(Holder<List<?>> listHolder){
        System.out.println(listHolder.value);
    }

    public static void main(String[] args) {
        Holder raw = new Holder();
        Holder<List<?>> listHolder = new Holder<>(new ArrayList<Integer>());
        f1(raw);
        f1(listHolder);
    }

}
