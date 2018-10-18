package ch11;

import org.junit.Assert;

import java.util.Arrays;
import java.util.LinkedList;
/**
 * @author yuzhe
 * @since 8/20/18
 */
public class LinkedListFeatures {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        list.offer(10);
        Assert.assertTrue(list.size() == 10);
        list.remove();
    }

}
