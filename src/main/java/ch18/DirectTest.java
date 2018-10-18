package ch18;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.io.*;

/**
 * @author yuzhe
 * @since 10/15/18
 */
public class DirectTest {

    private static final int SIZE = 4000000;
    private static final String PATH = "src/main/resources/ch18/DirectTestFile";

    public static void main(String[] args) {
        Tester[] tests = new Tester[]{
                new Tester("allocate write") {
                    @Override
                    protected void test() throws IOException {
                        ByteBuffer bf = ByteBuffer.allocate(SIZE);
                        FileChannel fc = new FileOutputStream(new File(PATH)).getChannel();
                        for (int i = 0; i < SIZE; i++) {
                            bf.put((byte) i);
                        }
                        fc.write(bf);
                    }
                },
                new Tester("allocate direct write") {
                    @Override
                    protected void test() throws IOException {
                        ByteBuffer bf = ByteBuffer.allocateDirect(SIZE);
                        FileChannel fc = new FileOutputStream(new File(PATH)).getChannel();
                        for (int i = 0; i < SIZE; i++) {
                            bf.put((byte) i);
                        }
                        fc.write(bf);
                    }
                }
        };

        for (Tester tester : tests) {
            tester.runTest();
        }
    }


}
