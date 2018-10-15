package code.ch18;

import org.apache.commons.compress.utils.CharsetNames;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author yuzhe
 * @since 10/15/18
 */
public class Exec23 {

    public static void main(String[] args) throws IOException {
        FileChannel fc = new FileOutputStream("src/resources/ch18/data3.txt").getChannel();
        fc.write(ByteBuffer.wrap("hello, world".getBytes(CharsetNames.UTF_8)));
        fc.close();

        fc = new FileInputStream("src/resources/ch18/data3.txt").getChannel();
        ByteBuffer bf = ByteBuffer.allocate(64);
        bf.clear();
        fc.read(bf);
        bf.flip();
        CharBuffer cb = Charset.forName(CharsetNames.UTF_8).decode(bf);
        for(int i =0 ; i < cb.length(); i++){
            System.out.print(cb.charAt(i));
        }

        System.out.println();
        System.out.println("-----------------------------------------------");

        fc = new FileOutputStream("src/resources/ch18/data4.txt").getChannel();
        //UTF_16, UTF_16BE, UTF_16LE
        fc.write(ByteBuffer.wrap("hello, world".getBytes(CharsetNames.UTF_16)));
        fc.close();

        fc = new FileInputStream("src/resources/ch18/data4.txt").getChannel();
        bf = ByteBuffer.allocate(64);
        bf.clear();
        fc.read(bf);
        bf.flip();
        cb = bf.asCharBuffer();
        for(int i =0 ; i < cb.length(); i++){
            System.out.print(cb.charAt(i));
        }
    }

}
