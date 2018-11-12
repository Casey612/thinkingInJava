package ch17;

import java.util.PriorityQueue;

/**
 * @author: yuki
 * @date: 2018/10/6
 */
public class Exec11 extends PriorityQueue<Exec11.Container> {

    static class Container implements Comparable<Container> {
        private int i;

        public Container(int i) {
            this.i = i;
        }

        @Override
        public int compareTo(Container o) {
            return Integer.valueOf(i).compareTo(o.i);
        }

        @Override
        public String toString() {
            return "container-i:" + i;
        }
    }

    public void add(int i){
        super.add(new Container(i));
    }

    public static void main(String[] args) {
        Exec11 queue = new Exec11();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        queue.add(6);

        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }

}
