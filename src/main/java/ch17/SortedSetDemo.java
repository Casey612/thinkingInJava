package ch17;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author yuzhe
 * @since 9/25/18
 */
public class SortedSetDemo {

    public static void main(String[] args) {
        SortedSet<String> sortedSet = new TreeSet<>();
        Collections.addAll(sortedSet, "one two three four five six seven eight".split(" "));
        System.out.println(sortedSet);
        String low = sortedSet.first();
        String high = sortedSet.last();

        System.out.println(low);
        System.out.println(high);

        Iterator<String> it = sortedSet.iterator();

        for(int i = 0; i <= 7; i++){
            if(i == 3){
                low = it.next();
            }else if(i == 7){
                high = it.next();
            }
            else{
                it.next();
            }
        }

        System.out.println(low);
        System.out.println(high);

    }

}
