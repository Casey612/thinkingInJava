package code.ch18;

import java.io.*;

/**
 * @author yuzhe
 * @since 10/12/18
 */
public class Redirecting {

    public static void main(String[] args) throws IOException {
        PrintStream console = System.out;
        BufferedInputStream in = new BufferedInputStream(
                new FileInputStream("src/java/code/ch18/Redirecting.java")
        );
        PrintStream out = new PrintStream(
                new BufferedOutputStream(
                        new FileOutputStream("src/resources/ch18/test.out")
                )
        );
        System.setIn(in);
        System.setOut(out);
        System.setErr(out);

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );
        String s;
        while((s = br.readLine()) != null){
            System.out.println(s);
        }
        out.close();
        System.setOut(console);
    }

}
