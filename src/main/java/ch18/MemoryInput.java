package java.ch18;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author yuzhe
 * @since 10/9/18
 */
public class MemoryInput {

    public static void main(String[] args) throws IOException {
        StringReader in = new StringReader(
                BufferedInputFile.read("src/java/java/ch18/MemoryInput.java")
        );
        int c;
        while((c = in.read() ) != -1){
            System.out.print((char) c);
        }

    }

}
