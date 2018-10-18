package ch18;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author yuzhe
 * @since 10/12/18
 */
public class ChannelCopy {

    private static final int SIZE = 1024;

    public static void main(String[] args) throws IOException {
        String input = "src/main/resources/ch18/CopyTarget";
        String output = "src/main/resources/ch18/CopyFile";

        FileChannel
                in = new FileInputStream(input).getChannel(),
                out = new FileOutputStream(output).getChannel();

        ByteBuffer buff = ByteBuffer.allocate(SIZE);
        while(in.read(buff) != -1){
            buff.flip();
            out.write(buff);
            buff.clear();
        }

    }

}
