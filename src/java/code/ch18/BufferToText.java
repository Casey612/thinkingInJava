package code.ch18;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author yuzhe
 * @since 10/15/18
 */
public class BufferToText {

    private static final int SIZE = 1024;

    public static void main(String[] args) throws IOException {
        FileChannel fc = new FileOutputStream("src/resources/ch18/data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("some text".getBytes()));
        fc.close();

        //直接由ByteBuffer转charBudder GG
        fc = new FileInputStream("src/resources/ch18/data2.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(SIZE);
        fc.read(buff);
        buff.flip();
        System.out.println(buff.asCharBuffer());

        System.out.println("----------------------------------------------------");

        //写出时解码
        buff.rewind();
        String encoding = System.getProperty("file.encoding");
        System.out.println("decoded using " + encoding + ": " + Charset.forName(encoding).decode(buff));

        System.out.println("----------------------------------------------------");

        //写入时编码，则写出时charBuffer可用
        fc = new FileOutputStream("src/resources/ch18/data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("some text".getBytes("UTF-16BE")));
        fc.close();

        fc = new FileInputStream("src/resources/ch18/data2.txt").getChannel();
        buff.clear();
        fc.read(buff);
        buff.flip();
        System.out.println("UTF-16BE:" + buff.asCharBuffer());

        System.out.println("----------------------------------------------------");

        //24个字节ByteBuffer转CharBuffer12个字节 多余字节无内容为空
        fc = new FileOutputStream("src/resources/ch18/data2.txt").getChannel();
        buff = ByteBuffer.allocate(24);
        buff.asCharBuffer().put("some text");
        fc.write(buff);
        fc.close();

        fc = new FileInputStream("src/resources/ch18/data2.txt").getChannel();
        buff.clear();
        fc.read(buff);
        buff.flip();
        System.out.println(buff.asCharBuffer());
    }

}
