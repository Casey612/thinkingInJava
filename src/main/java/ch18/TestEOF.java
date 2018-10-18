package ch18;

import java.io.*;

/**
 * @author yuzhe
 * @since 10/9/18
 */
public class TestEOF {

    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("src/main/java/ch18/TestEOF.java")
                )
        );
        while (in.available() != 0) {
            System.out.print((char) in.readByte());
        }

    }

}
