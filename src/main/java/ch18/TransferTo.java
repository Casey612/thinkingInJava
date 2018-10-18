package java.ch18;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author yuzhe
 * @since 10/15/18
 */
public class TransferTo {

    public static void main(String[] args) throws IOException {
        String input = "src/resources/ch18/transferFrom";
        String output = "src/resources/ch18/transferTo";

        FileChannel
                in = new FileInputStream(input).getChannel(),
                out = new FileOutputStream(output).getChannel();

        in.transferTo(0, in.size(), out);

    }

}
