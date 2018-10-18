package ch11;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author yuzhe
 * @since 8/21/18
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(25, 22, 20, 18, 14, 9, 3, 1, 1, 2, 3, 9, 14, 18, 21, 23, 25);

        PriorityQueue<Integer> queue1 = new PriorityQueue<>(ints);
        PriorityQueue<Integer> queue2 = new PriorityQueue<>(ints.size(), Collections.reverseOrder());
        queue2.addAll(ints);
        printQueue(queue1);
        printQueue(queue2);

    }

    public static <E> void printQueue(PriorityQueue<E> queue) {
        while(!queue.isEmpty()){
            System.out.print(queue.poll());
            System.out.print(" ");
        }
        System.out.println();
    }

}
