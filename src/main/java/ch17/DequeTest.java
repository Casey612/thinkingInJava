package ch17;

/**
 * @author: yuki
 * @date: 2018/10/6
 */
public class DequeTest {

    private static void fillDeque(Deque<Integer> deque) {
        for (int i = 20; i < 27; i++) {
            deque.addFirst(i);
        }

        for (int i = 50; i < 57; i++) {
            deque.addLast(i);
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        fillDeque(deque);

        System.out.println(deque);

        while (deque.size() > 0) {
            System.out.print(deque.removeFirst() + " ");
        }

        fillDeque(deque);
        System.out.println();

        while (deque.size() > 0) {
            System.out.print(deque.removeLast() + " ");
        }
    }

}
