package ch18;

import java.io.IOException;

/**
 * @author yuzhe
 * @since 10/15/18
 */
public abstract class Tester {

    private String name;

    public Tester(String name) {
        this.name = name;
    }

    public void runTest() {
        System.out.println(name + ":");
        try {
            long start = System.nanoTime();
            test();
            double duration = System.nanoTime() - start;
            System.out.format("%.2f\n", duration / 1.0e9);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void test() throws IOException;

}
