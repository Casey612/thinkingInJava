package code.ch18;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * @author yuzhe
 * @since 10/15/18
 */
public class MappedIO {

    private static int numOfInts = 4000000;
    private static int numOfUbuffInts = 200000;
    private static String PATH = "src/resources/ch18/temp.tmp";


    private static Tester[] tests = {
            new Tester("Stream Write") {
                @Override
                protected void test() throws IOException {
                    DataOutputStream dos = new DataOutputStream(
                            new BufferedOutputStream(
                                    new FileOutputStream(new File(PATH))
                            )
                    );
                    for (int i = 0; i < numOfInts; i++) {
                        dos.writeInt(i);
                    }
                    dos.close();
                }
            },
            new Tester("Stream Read") {
                @Override
                protected void test() throws IOException {
                    DataInputStream dis = new DataInputStream(
                            new BufferedInputStream(
                                    new FileInputStream(new File(PATH))
                            )
                    );
                    for (int i = 0; i < numOfInts; i++) {
                        dis.readInt();
                    }
                    dis.close();
                }
            },
            new Tester("Stream Read/Write") {
                @Override
                protected void test() throws IOException {
                    RandomAccessFile raf = new RandomAccessFile(new File(PATH), "rw");
                    raf.writeInt(1);
                    for (int i = 0; i < numOfUbuffInts; i++) {
                        raf.seek(raf.length() - 4);
                        raf.writeInt(raf.readInt());
                    }
                    raf.close();
                }
            },
            new Tester("Mapped Read") {
                @Override
                protected void test() throws IOException {
                    FileChannel fc = new FileInputStream(new File(PATH)).getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).asIntBuffer();
                    while (ib.hasRemaining()) {
                        ib.get();
                    }
                    fc.close();
                }
            },
            new Tester("Mapped Write") {
                @Override
                protected void test() throws IOException {
                    FileChannel fc = new RandomAccessFile(PATH, "rw").getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
                    for (int i = 0; i < numOfInts; i++) {
                        ib.put(i);
                    }
                    fc.close();
                }
            },
            new Tester("Mapped Read/Write") {
                @Override
                protected void test() throws IOException {
                    FileChannel fc = new RandomAccessFile(PATH, "rw").getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
                    ib.put(0);
                    for (int i = 1; i < numOfUbuffInts; i++) {
                        ib.put(ib.get(i - 1));
                    }
                    fc.close();
                }
            }
    };

    public static void main(String[] args) {
        for(Tester tester : tests){
            tester.runTest();
        }
    }


}
