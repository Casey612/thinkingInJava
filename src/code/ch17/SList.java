package thinkingInJava.ch17;

import lombok.Getter;

import java.util.Iterator;

/**
 * @author yuzhe
 * @since 9/25/18
 */
public class SList<T> {

    private Node<T> root;
    private int size;


    private class Node<T> {
        @Getter
        private T value;
        @Getter
        private Node<T> next;

        public Node(T t) {
            this.value = t;
            this.next = null;
        }
    }

    @Override
    public String toString() {
        if (root == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Iterator it = iterator();
        while (it.hasNext()) {
            Node<T> indexNode = (Node<T>) it.next();
            sb.append(indexNode.value).append(',');
        }
        sb.deleteCharAt(sb.length() - 1).append(']');
        return sb.toString();
    }

    public boolean add(int index, T t) {
        if(index > size){
            throw new ArrayIndexOutOfBoundsException();
        }
        Iterator it = iterator();
        int count = 0;
        Node<T> indexNode = null;
        while (count < index && it.hasNext()) {
            indexNode = (Node<T>) it.next();
            count++;
        }
        Node<T> newNode = new Node<>(t);
        if (indexNode != null) {
            newNode.next = indexNode.next;
            indexNode.next = newNode;
        } else {
            //root 结点
            root = newNode;
        }
        size++;
        return true;
    }


    public Iterator iterator() {
        return new Iterator() {
            private Node<T> index = root;

            @Override
            public boolean hasNext() {
                return root != null && index != null;
            }

            @Override
            public Node<T> next() {
                Node<T> result = index;
                index = index.next;
                return result;
            }
        };
    }

    public static void main(String[] args) {
        SList<Integer> sl = new SList<>();
        for (int i = 0; i < 10; i++) {
            sl.add(i, i);
        }
        System.out.println(sl.toString());
    }
}
