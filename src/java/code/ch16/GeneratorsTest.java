package code.ch16;

import code.ch15.Generator;

/**
 * @author yuzhe
 * @since 9/14/18
 */
public class GeneratorsTest {

    public static int size = 10;

    public static void test(Class<?> surroundintClass) {
        for (Class<?> type : surroundintClass.getClasses()) {
            System.out.print(type.getSimpleName() + ": ");
            try {
                Generator<?> g = (Generator<?>) type.newInstance();
                for(int i = 0; i < size; i++){
                    System.out.print(g.next() + " ");
                }
                System.out.println();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        test(CountingGenerator.class);
        test(RandomGenerator.class);
    }

}
