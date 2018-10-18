package java.ch18;

import java.io.*;

/**
 * @author: yuki
 * @date: 2018/10/9
 */
public class BasicFileOutput {

    static String file = "src/resources/BasicFileOutput.out";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(
                new StringReader(
                        BufferedInputFile.read("src/java/java/ch18/BasicFileOutput.java")
                )
        );
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(file))
        );

        int lineCount = 1;
        String s;
        while((s=in.readLine()) != null){
            out.println(lineCount++ + ":" + s);
        }
        in.close();
        out.close();
        System.out.println(BufferedInputFile.read(file));

    }

}
