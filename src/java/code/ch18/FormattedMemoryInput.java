package code.ch18;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/**
 * @author yuzhe
 * @since 10/9/18
 */
public class FormattedMemoryInput {

    public static void main(String[] args) {
        try {
            DataInputStream in = new DataInputStream(
                    new ByteArrayInputStream(
                            BufferedInputFile.read("src/java/code/ch18/FormattedMemoryInput.java").getBytes()
                    )
            );
            while (true) {
                System.out.print((char) in.readByte());
            }
        } catch (Exception e) {
            System.out.println("end of stream");
        }

    }

}
