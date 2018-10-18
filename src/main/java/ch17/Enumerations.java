package ch17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

/**
 * @author: yuki
 * @date: 2018/10/7
 */
public class Enumerations {

    public static void main(String[] args) {
        Vector<String> v = new Vector<>(Countries.names(10));
        Enumeration<String> e = v.elements();
        while(e.hasMoreElements()) {
            System.out.print(e.nextElement() + ",");
        }
        e = Collections.enumeration(new ArrayList<>());
    }

}
