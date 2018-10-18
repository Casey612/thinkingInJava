package java.ch18;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * @author yuzhe
 * @since 10/16/18
 */
public class FileLocking {

    public static void main(String[] args) throws IOException, InterruptedException {
        FileOutputStream fos = new FileOutputStream("src/resources/file.txt");
        FileLock fl = fos.getChannel().tryLock();
        if(fl != null){
            System.out.println("lock file");
            TimeUnit.MILLISECONDS.sleep(100);
            fl.release();
            System.out.println("release file");
        }
        fos.close();
    }


}
