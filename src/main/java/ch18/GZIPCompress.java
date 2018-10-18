package java.ch18;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author yuzhe
 * @since 10/16/18
 */
public class GZIPCompress {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(
                new FileReader("src/resources/ch18/data.txt")
        );
        BufferedOutputStream out = new BufferedOutputStream(
                new GZIPOutputStream(
                        new FileOutputStream("src/resources/ch18/data.txt.gz")
                )
        );
        System.out.println("writing file");
        int c;
        while ((c = in.read()) != -1) {
            out.write(c);
        }
        out.flush();
        in.close();
        out.close();
        System.out.println("reading file:");
        BufferedReader in2 = new BufferedReader(
                new InputStreamReader(new GZIPInputStream(
                        new FileInputStream("src/resources/ch18/data.txt.gz")
                ))
        );
        String s;
        while ((s = in2.readLine()) != null) {
            System.out.println(s);
        }
        in2.close();
    }

}
