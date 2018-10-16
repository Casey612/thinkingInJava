package code.ch18;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @author yuzhe
 * @since 10/16/18
 */
public class LockingMappedFiles {

    private static final int LENGTH = 64;
    private static FileChannel fc;

    public static void main(String[] args) throws IOException {
        fc = new RandomAccessFile("src/resources/test.txt", "rw").getChannel();
        MappedByteBuffer out = fc.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            out.put((byte) 'x');
        }
        new LockAndModify(out, 0, 0 + LENGTH / 3);
        new LockAndModify(out, LENGTH / 2, LENGTH / 2 + LENGTH / 4);
    }

    private static class LockAndModify extends Thread{
        private int start, end;
        private ByteBuffer buff;
        public LockAndModify(ByteBuffer out, int start, int end) {
            this.start = start;
            this.end = end;
            out.limit(end);
            out.position(start);
            buff = out.slice();
            start();
        }

        @Override
        public void run(){
            try {
                FileLock fl = fc.tryLock(start, end, false);
                System.out.println("locked: " + start + " to " + end);
                while(buff.position() <  buff.limit() - 1){
                    buff.put((byte)(buff.get() + 1));
                }
                fl.release();
                System.out.println("release: " + start + " to " + end);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
