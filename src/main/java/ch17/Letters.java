package java.ch17;

import java.ch15.Generator;
import java.ch16.CountingGenerator;
import java.ch16.RandomGenerator;

import java.util.Iterator;


/**
 * @author: yuki
 * @date: 2018/9/20
 */
public class Letters implements Generator<Pair<Integer, String>>, Iterable<Integer> {

    private int size = 9;
    private int number = 1;
    private char letter = 'A';

    @Override
    public Pair<Integer, String> next() {
        return new Pair<Integer, String>(number++, letter++ + "");
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return number < size;
            }

            @Override
            public Integer next() {
                return number++;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

}

class MapDataTest {
    public static void main(String[] args) {
        System.out.println(MapData.map(new Letters(), 11));

        System.out.println(new MapData(new CountingGenerator.Character(),
                new RandomGenerator.Integer(3), 8));

        System.out.println(new MapData(new Letters(), new RandomGenerator.Character()));
    }
}
