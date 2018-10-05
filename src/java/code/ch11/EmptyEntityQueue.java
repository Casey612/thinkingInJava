package code.ch11;

import java.util.PriorityQueue;

/**
 * @author yuzhe
 * @since 8/21/18
 */
public class EmptyEntityQueue {

    public static void main(String[] args) {
        PriorityQueue<EmptyEntity> queue = new PriorityQueue<>();
        for(int i = 0; i < 5; i++){
            queue.offer(new EmptyEntity());
        }
        printQueue(queue);
    }

    public static <E> void printQueue(PriorityQueue<E> queue) {
        while(!queue.isEmpty()){
            System.out.print(queue.poll());
            System.out.print(" ");
        }
        System.out.println();
    }

}
