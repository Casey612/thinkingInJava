package ch18;

import java.io.*;

/**
 * @author: yuki
 * @date: 2018/10/9
 */
public class Exec13 {

    static String file = "src/main/resources/Exec13.out";

    public static void main(String[] args) throws IOException {
        LineNumberReader in = new LineNumberReader(
                new StringReader(
                        BufferedInputFile.read("src/main/java/ch18/Exec13.java")
                )
        );
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(file))
        );

        String s;
        while ((s = in.readLine()) != null) {
            out.println(in.getLineNumber() + ":" + s);
        }
        in.close();
        out.close();
        System.out.println(BufferedInputFile.read(file));

    }

}
