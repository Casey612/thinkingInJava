package code.ch17;

import java.util.*;

/**
 * @author yuzhe
 * @since 9/25/18
 */
public class Exec10 {

    public class SortedSet<T extends Comparable> implements java.util.SortedSet<T> {

        private LinkedList<T> array = new LinkedList<>();

        public SortedSet(Collection<T> list) {
            this.array = new LinkedList<>();
            array.addAll(list);
        }

        @Override
        public Comparator<? super T> comparator() {
            return (Comparator<T>) (o1, o2) -> o1.compareTo(o2);
        }

        @Override
        public java.util.SortedSet<T> subSet(T fromElement, T toElement) {
            int from = array.indexOf(fromElement);
            int to = array.indexOf(toElement);
            return new SortedSet<T>(array.subList(from, to));
        }

        @Override
        public java.util.SortedSet<T> headSet(T toElement) {
            return null;
        }

        @Override
        public java.util.SortedSet<T> tailSet(T fromElement) {
            int from = array.indexOf(fromElement);
            return new SortedSet<T>(array.subList(from, array.size()));
        }

        @Override
        public T first() {
            return array.getFirst();
        }

        @Override
        public T last() {
            return array.getLast();
        }

        @Override
        public int size() {
            return array.size();
        }

        @Override
        public boolean isEmpty() {
            return array.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return array.contains(o);
        }

        @Override
        public Iterator<T> iterator() {
            return array.iterator();
        }

        @Override
        public Object[] toArray() {
            return array.toArray();
        }

        @Override
        public <T1> T1[] toArray(T1[] a) {
            return array.toArray(a);
        }

        @Override
        public boolean add(T t) {
            return array.add(t);
        }

        @Override
        public boolean remove(Object o) {
            return array.remove(o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return array.containsAll(c);
        }

        @Override
        public boolean addAll(Collection<? extends T> c) {
            for (T t : c) {
                if(!array.contains(t)){
                    array.add(t);
                }
            }
            return true;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return retainAll(c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return array.removeAll(c);
        }

        @Override
        public void clear() {
            array.clear();
        }
    }

}
